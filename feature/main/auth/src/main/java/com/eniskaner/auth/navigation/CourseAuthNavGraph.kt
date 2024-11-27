package com.eniskaner.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.fragment.fragment
import androidx.navigation.navigation
import com.eniskaner.auth.ui.view.CourseAuthFragment
import com.eniskaner.auth.ui.view.SignInFragment
import com.eniskaner.auth.ui.view.SignUpFragment
import com.eniskaner.navigationcourseapp.NavigationGraph
import javax.inject.Inject

class CourseAuthNavGraph @Inject constructor() : NavigationGraph {

    override fun addNav(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.apply {
            navigation(startDestination = START_DESTINATION, route = ROUTE) {
                fragment<CourseAuthFragment>(START_DESTINATION)
                fragment<SignInFragment>(LOGIN_DESTINATION)
                fragment<SignUpFragment>(REGISTER_DESTINATION)
            }
        }
    }

    companion object {
        const val ROUTE = "course_auth_route"
        const val START_DESTINATION = "course_auth_screen"
        const val LOGIN_DESTINATION = "login_screen"
        const val REGISTER_DESTINATION = "register_screen"
    }
}
