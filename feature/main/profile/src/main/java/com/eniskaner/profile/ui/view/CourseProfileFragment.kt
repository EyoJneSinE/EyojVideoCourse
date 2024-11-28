package com.eniskaner.profile.ui.view

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.eniskaner.common.preferences.PreferencesManager
import com.eniskaner.common.util.launchAndRepeatWithViewLifecycle
import com.eniskaner.common.util.viewBinding
import com.eniskaner.coursecommunicator.CourseAuthQualifierForProfileScreen
import com.eniskaner.coursecommunicator.CourseFeatureCommunicator
import com.eniskaner.profile.R
import com.eniskaner.profile.databinding.FragmentCourseProfileBinding
import com.eniskaner.profile.navigation.CourseProfileNavGraph
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CourseProfileFragment : Fragment(R.layout.fragment_course_profile) {

    private val binding: FragmentCourseProfileBinding by viewBinding(FragmentCourseProfileBinding::bind)

    private lateinit var auth: FirebaseAuth
    private var user: String? = null

    @Inject
    @CourseAuthQualifierForProfileScreen
    lateinit var courseAuthCommunicator: CourseFeatureCommunicator

    @Inject
    lateinit var preferencesManager: PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth
        user = auth.currentUser?.email
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUserInteraction()

        launchAndRepeatWithViewLifecycle {
            updateUIWithLoggedInUser()
        }

        val isDarkMode = preferencesManager.isDarkMode
        updateModeUI(isDarkMode)

        binding.switchMode.setOnCheckedChangeListener { _, isChecked ->
            preferencesManager.isDarkMode = isChecked
            updateModeUI(isChecked)
            toggleDarkMode(isChecked)
        }
    }

    private fun setUserInteraction() = with(binding) {
        btnLogin.setOnClickListener { openAuthNavigation() }
        btnLogout.setOnClickListener { onLogout() }
    }

    private fun updateUIWithLoggedInUser() = with(binding) {
        user = auth.currentUser?.email
        user?.let {
            progressUser.isVisible = true
            updateVisibilityWith(it)
            val fullName = it
            txtFullName.text = fullName
            txtAvatarLabel.text = fullName.take(1)
            txtPhone.text = getString(R.string.phone_number)
            txtAddress.text = getString(R.string.user_adress)
            progressUser.isVisible = false
        }
    }

    private fun onLogout() = with(binding) {
        FirebaseAuth.getInstance().signOut()
        user = null
        btnLogin.isVisible = user != null
        cardviewProfile.isVisible = user == null
        btnLogout.isVisible = user == null
        courseAuthCommunicator.launchCourseFeature(
            CourseFeatureCommunicator.CourseFeatureArgs(
                previousRoute = CourseProfileNavGraph.ROUTE
            )
        )
    }

    private fun updateVisibilityWith(user: String?) = with(binding) {
        btnLogin.isVisible = user == null
        cardviewProfile.isVisible = user != null
        btnLogout.isVisible = user != null
    }

    private fun openAuthNavigation() {
        courseAuthCommunicator.launchCourseFeature(
            CourseFeatureCommunicator.CourseFeatureArgs(
                previousRoute = CourseProfileNavGraph.ROUTE
            )
        )
    }

    private fun updateModeUI(isDarkMode: Boolean) {
        binding.tvMode.text = if (isDarkMode) getString(R.string.dark_mode) else getString(R.string.light_mode)
        binding.switchMode.isChecked = isDarkMode
    }

    private fun toggleDarkMode(enable: Boolean) {
        val mode = if (enable) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        AppCompatDelegate.setDefaultNightMode(mode)

        requireActivity().recreate()
    }
}
