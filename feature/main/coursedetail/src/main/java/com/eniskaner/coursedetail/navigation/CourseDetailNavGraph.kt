package com.eniskaner.coursedetail.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.fragment.fragment
import androidx.navigation.navigation
import com.eniskaner.coursedetail.ui.view.CourseDetailFragment
import com.eniskaner.navigationcourseapp.NavigationGraph
import javax.inject.Inject

class CourseDetailNavGraph @Inject constructor() : NavigationGraph {

    override fun addNav(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.apply {
            navigation(startDestination = START_DESTINATION, route = ROUTE) {
                fragment<CourseDetailFragment>(START_DESTINATION)
            }
        }
    }

    companion object {
        const val ROUTE = "course_detail_route"
        const val START_DESTINATION = "course_detail_screen"
    }
}
