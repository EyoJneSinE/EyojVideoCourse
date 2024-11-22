package com.eniskaner.coursecommunicator

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

interface VideoCourseCommunicator {

    fun launchVideoCourseFeature(videoCourseFeatureArgs: VideoCourseFeatureArgs)

    companion object {
        const val VIDEO_COURSE_FEATURE_NAV_KEY = "videoCourseFeatureNavKey"
    }

    @Parcelize
    data class VideoCourseFeatureArgs(
        val previousRoute: String,
        val courseId: Int? = null,
        val courseCategoryId: Int? = null
    ) : Parcelable
}
