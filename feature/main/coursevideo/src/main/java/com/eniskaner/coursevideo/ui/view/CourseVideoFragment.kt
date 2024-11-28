package com.eniskaner.coursevideo.ui.view

import android.os.Bundle
import android.view.View
import androidx.annotation.OptIn
import androidx.fragment.app.Fragment
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import com.eniskaner.common.preferences.PreferencesManager
import com.eniskaner.common.util.Constants.ExoPlayerConstants.PLAYBACK_POSITION_KEY
import com.eniskaner.common.util.Constants.ExoPlayerConstants.PLAY_WHEN_READY_KEY
import com.eniskaner.common.util.parcelable
import com.eniskaner.common.util.viewBinding
import com.eniskaner.coursecommunicator.CourseFeatureCommunicator
import com.eniskaner.coursevideo.R
import com.eniskaner.coursevideo.databinding.FragmentCourseVideoBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@UnstableApi
@AndroidEntryPoint
class CourseVideoFragment : Fragment(R.layout.fragment_course_video) {

    private val binding: FragmentCourseVideoBinding by viewBinding(FragmentCourseVideoBinding::bind)

    private var exoPlayer: ExoPlayer? = null
    private var playbackPosition = 0L
    private var playWhenReady = true

    @Inject
    lateinit var preferencesManager: PreferencesManager
    private var lessonId: Int? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        savedInstanceState?.let {
            playbackPosition = it.getLong(PLAYBACK_POSITION_KEY, 0L)
            playWhenReady = it.getBoolean(PLAY_WHEN_READY_KEY, true)
            exoPlayer?.playWhenReady = playWhenReady
            exoPlayer?.seekTo(playbackPosition)
        }

        val args = arguments?.parcelable<CourseFeatureCommunicator.CourseFeatureArgs>(
            CourseFeatureCommunicator.COURSE_FEATURE_NAV_KEY
        )

        lessonId = args?.lessonId
        lessonId?.let {
            playbackPosition = preferencesManager.getVideoProgress(it)
        }
        args?.videoUrl?.let {
            preparePlayer(it)
        }

    }

    @OptIn(UnstableApi::class)
    private fun preparePlayer(uri: String) {
        exoPlayer = ExoPlayer.Builder(requireContext()).build().apply {
            playWhenReady = this@CourseVideoFragment.playWhenReady
            setMediaItem(MediaItem.fromUri(uri))
            seekTo(playbackPosition)
            prepare()
        }
        binding.playerView.player = exoPlayer
    }

    private fun releasePlayer() {
        exoPlayer?.let { player ->
            playbackPosition = player.currentPosition
            playWhenReady = player.playWhenReady
            lessonId?.let {
                preferencesManager.saveVideoProgress(it, playbackPosition)
            }
            player.release()
            exoPlayer = null
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        exoPlayer?.let { player ->
            outState.putLong(PLAYBACK_POSITION_KEY, player.currentPosition)
            outState.putBoolean(PLAY_WHEN_READY_KEY, player.playWhenReady)
        }
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    override fun onPause() {
        super.onPause()
        releasePlayer()
    }

}
