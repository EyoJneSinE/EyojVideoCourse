package com.eniskaner.coursecommunicator

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

interface CourseFeatureCommunicator {

    fun launchCourseFeature(courseFeatureArgs: CourseFeatureArgs)

    companion object {
        const val COURSE_FEATURE_NAV_KEY = "courseFeatureNavKey"
    }

    @Parcelize
    data class CourseFeatureArgs(
        val previousRoute: String,
        val courseId: Int? = null,
        val courseCategoryId: Int? = null,
        val videoUrl: String? = null
    ) : Parcelable
}
