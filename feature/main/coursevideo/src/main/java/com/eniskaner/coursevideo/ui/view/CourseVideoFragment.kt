package com.eniskaner.coursevideo.ui.view

import android.os.Bundle
import android.view.View
import androidx.annotation.OptIn
import androidx.fragment.app.Fragment
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.hls.HlsMediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import com.eniskaner.common.util.parcelable
import com.eniskaner.common.util.viewBinding
import com.eniskaner.coursecommunicator.CourseFeatureCommunicator
import com.eniskaner.coursevideo.databinding.FragmentCourseVideoBinding

class CourseVideoFragment : Fragment() {

    private val binding: FragmentCourseVideoBinding by viewBinding(FragmentCourseVideoBinding::bind)

    private var exoPlayer: ExoPlayer? = null
    private var playbackPosition = 0L
    private var playWhenReady = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args =
            arguments?.parcelable(CourseFeatureCommunicator.COURSE_FEATURE_NAV_KEY) as? CourseFeatureCommunicator.CourseFeatureArgs
        args?.videoUrl?.let {

        }
        preparePlayer()
    }

    @OptIn(UnstableApi::class)
    private fun preparePlayer() {
        val args =
            arguments?.parcelable(CourseFeatureCommunicator.COURSE_FEATURE_NAV_KEY) as? CourseFeatureCommunicator.CourseFeatureArgs

        exoPlayer = ExoPlayer.Builder(requireContext()).build()
        exoPlayer?.playWhenReady = true
        binding.playerView.player = exoPlayer
        val defaultHttpDataSourceFactory = DefaultHttpDataSource.Factory()
        args?.videoUrl?.let {
            val mediaItem = MediaItem.fromUri(it)
            val mediaSource =
                ProgressiveMediaSource.Factory(defaultHttpDataSourceFactory)
                    .createMediaSource(mediaItem)
            exoPlayer?.apply {
                setMediaSource(mediaSource)
                seekTo(playbackPosition)
                playWhenReady = playWhenReady
                prepare()
            }
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

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    override fun onPause() {
        super.onPause()
        releasePlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        releasePlayer()
    }
}