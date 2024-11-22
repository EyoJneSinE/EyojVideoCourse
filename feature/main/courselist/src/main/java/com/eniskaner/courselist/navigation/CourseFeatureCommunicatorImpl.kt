package com.eniskaner.courselist.navigation

import androidx.navigation.NavController
import com.eniskaner.coursecommunicator.VideoCourseCommunicator
import com.eniskaner.navigationcourseapp.navigateWithAnimation
import javax.inject.Inject

class CourseFeatureCommunicatorImpl @Inject constructor(
    private val navController: NavController
) : VideoCourseCommunicator {

    override fun launchVideoCourseFeature(videoCourseFeatureArgs: VideoCourseCommunicator.VideoCourseFeatureArgs) {
        navController.navigateWithAnimation(
            route = CoursesNavGraph.ROUTE,
            popUpTo = videoCourseFeatureArgs.previousRoute,
            inclusive = false
        )
    }
}
