package com.eniskaner.courselist.navigation

import androidx.navigation.NavController
import com.eniskaner.coursecommunicator.CourseFeatureCommunicator
import com.eniskaner.navigationcourseapp.navigateWithAnimation
import javax.inject.Inject

class CourseFeatureCommunicatorImpl @Inject constructor(
    private val navController: NavController
) : CourseFeatureCommunicator {

    override fun launchCourseFeature(courseFeatureArgs: CourseFeatureCommunicator.CourseFeatureArgs) {
        navController.navigateWithAnimation(
            route = CoursesNavGraph.ROUTE,
            popUpTo = courseFeatureArgs.previousRoute,
            inclusive = false
        )
    }
}
