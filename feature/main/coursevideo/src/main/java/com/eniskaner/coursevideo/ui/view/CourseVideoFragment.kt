package com.eniskaner.coursevideo.ui.view

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.annotation.OptIn
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import com.eniskaner.common.util.Constants.ExoPlayerConstants.PLAYBACK_POSITION_KEY
import com.eniskaner.common.util.Constants.ExoPlayerConstants.PLAY_WHEN_READY_KEY
import com.eniskaner.common.util.parcelable
import com.eniskaner.common.util.viewBinding
import com.eniskaner.coursecommunicator.CourseFeatureCommunicator
import com.eniskaner.coursevideo.R
import com.eniskaner.coursevideo.databinding.FragmentCourseVideoBinding
import com.eniskaner.coursevideo.ui.util.FullscreenHandler
import dagger.hilt.android.AndroidEntryPoint

@UnstableApi
@AndroidEntryPoint
class CourseVideoFragment : Fragment(R.layout.fragment_course_video) {

    private val binding: FragmentCourseVideoBinding by viewBinding(FragmentCourseVideoBinding::bind)

    private var exoPlayer: ExoPlayer? = null
    private var playbackPosition = 0L
    private var playWhenReady = true

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
        args?.videoUrl?.let {
            preparePlayer(it)
        }

        adjustLayoutForOrientation(resources.configuration.orientation)
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

    private fun adjustLayoutForOrientation(orientation: Int) {
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.playerView.layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
            )
            (activity as? FullscreenHandler)?.enterFullscreen()
        } else {
            binding.playerView.layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
            (activity as? FullscreenHandler)?.exitFullscreen()
        }
    }

    private fun releasePlayer() {
        exoPlayer?.let { player ->
            playbackPosition = player.currentPosition
            playWhenReady = player.playWhenReady
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

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        adjustLayoutForOrientation(newConfig.orientation)
    }
}