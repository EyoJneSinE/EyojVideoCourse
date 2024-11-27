package com.eniskaner.coursevideo.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.fragment.fragment
import androidx.navigation.navigation
import com.eniskaner.coursevideo.ui.view.CourseVideoFragment
import com.eniskaner.navigationcourseapp.NavigationGraph
import javax.inject.Inject

class CourseVideoNavGraph @Inject constructor() : NavigationGraph {

    override fun addNav(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.apply {
            navigation(startDestination = START_DESTINATION, route = ROUTE) {
                fragment<CourseVideoFragment>(START_DESTINATION)
            }
        }
    }

    companion object {
        const val ROUTE = "course_video_route"
        const val START_DESTINATION = "course_video_screen"
    }
}