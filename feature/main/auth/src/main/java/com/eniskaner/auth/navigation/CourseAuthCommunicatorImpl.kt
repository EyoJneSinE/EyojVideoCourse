package com.eniskaner.auth.navigation

import androidx.navigation.NavController
import com.eniskaner.coursecommunicator.CourseFeatureCommunicator
import com.eniskaner.navigationcourseapp.navigateWithAnimation
import javax.inject.Inject

class CourseAuthCommunicatorImpl @Inject constructor(
    private val navController: NavController
) : CourseFeatureCommunicator {

    override fun launchCourseFeature(courseFeatureArgs: CourseFeatureCommunicator.CourseFeatureArgs) {
        navController.navigateWithAnimation(
            route = CourseAuthNavGraph.ROUTE,
            popUpTo = courseFeatureArgs.previousRoute,
            inclusive = false
        )
    }
}
