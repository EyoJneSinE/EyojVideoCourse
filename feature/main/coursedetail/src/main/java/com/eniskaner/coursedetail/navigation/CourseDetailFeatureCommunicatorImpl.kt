package com.eniskaner.coursedetail.navigation

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.eniskaner.coursecommunicator.CourseFeatureCommunicator
import com.eniskaner.navigationcourseapp.navigationWithArgs
import javax.inject.Inject

class CourseDetailFeatureCommunicatorImpl @Inject constructor(
    private val navController: NavController
) : CourseFeatureCommunicator {

    override fun launchCourseFeature(courseFeatureArgs: CourseFeatureCommunicator.CourseFeatureArgs) {
        navController.navigationWithArgs(
            route = CourseDetailNavGraph.ROUTE,
            args = bundleOf(CourseFeatureCommunicator.COURSE_FEATURE_NAV_KEY to courseFeatureArgs)
        )
    }
}