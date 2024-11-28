package com.eniskaner.profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.fragment.fragment
import androidx.navigation.navigation
import com.eniskaner.navigationcourseapp.NavigationGraph
import com.eniskaner.profile.ui.view.CourseProfileFragment
import javax.inject.Inject

class CourseProfileNavGraph @Inject constructor() : NavigationGraph {

    override fun addNav(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.apply {
            navigation(startDestination = START_DESTINATION, route = ROUTE) {
                fragment<CourseProfileFragment>(START_DESTINATION)
            }
        }
    }

    companion object {
        const val ROUTE = "course_profile_route"
        const val START_DESTINATION = "course_profile_screen"
    }
}