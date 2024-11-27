package com.eniskaner.coursevideo.navigation

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.eniskaner.coursecommunicator.CourseFeatureCommunicator
import com.eniskaner.navigationcourseapp.navigationWithArgs
import javax.inject.Inject

class CourseVideoCommunicatorImpl @Inject constructor(
    private val navController: NavController
) : CourseFeatureCommunicator {

    override fun launchCourseFeature(courseFeatureArgs: CourseFeatureCommunicator.CourseFeatureArgs) {
        navController.navigationWithArgs(
            route = CourseVideoNavGraph.ROUTE,
            args = bundleOf(CourseFeatureCommunicator.COURSE_FEATURE_NAV_KEY to courseFeatureArgs)
        )
    }
}