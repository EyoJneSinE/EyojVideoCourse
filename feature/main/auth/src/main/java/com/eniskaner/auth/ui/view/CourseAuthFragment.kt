package com.eniskaner.auth.ui.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.eniskaner.auth.R
import com.eniskaner.auth.databinding.FragmentCourseAuthBinding
import com.eniskaner.auth.navigation.CourseAuthNavGraph
import com.eniskaner.common.util.viewBinding
import com.eniskaner.navigationcourseapp.navigateWithAnimation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CourseAuthFragment : Fragment(R.layout.fragment_course_auth) {

    private val binding: FragmentCourseAuthBinding by viewBinding(FragmentCourseAuthBinding::bind)

    private lateinit var auth: FirebaseAuth

    @Inject
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAuthUserInteractions()
    }

    private fun setAuthUserInteractions() = with(binding) {
        btnLogin.setOnClickListener {
            navController.navigateWithAnimation(
                route = CourseAuthNavGraph.LOGIN_DESTINATION
            )
        }
        btnRegister.setOnClickListener {
            navController.navigateWithAnimation(
                route = CourseAuthNavGraph.REGISTER_DESTINATION
            )
        }
    }
}
