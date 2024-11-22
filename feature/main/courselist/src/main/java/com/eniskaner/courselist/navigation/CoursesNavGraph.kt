package com.eniskaner.courselist.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.fragment.fragment
import androidx.navigation.navigation
import com.eniskaner.courselist.ui.view.CourseListFragment
import com.eniskaner.navigationcourseapp.NavigationGraph
import javax.inject.Inject

class CoursesNavGraph @Inject constructor() : NavigationGraph {
    override fun addNav(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.apply {
            navigation(startDestination = START_DESTINATION, route = ROUTE) {
                fragment<CourseListFragment>(START_DESTINATION)
            }
        }
    }

    companion object {
        const val ROUTE = "courses_route"
        const val START_DESTINATION = "course_list_screen"
    }
}
