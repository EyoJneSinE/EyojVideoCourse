package com.eniskaner.auth.ui.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.eniskaner.auth.R
import com.eniskaner.auth.databinding.FragmentSignUpBinding
import com.eniskaner.auth.navigation.CourseAuthNavGraph
import com.eniskaner.auth.ui.util.ConfirmPasswordTextWatcher
import com.eniskaner.common.util.Constants.FirebaseConstants.CONFIRM_PASSWORD_ERROR
import com.eniskaner.common.util.Constants.FirebaseConstants.FIREBASE_TAG
import com.eniskaner.common.util.viewBinding
import com.eniskaner.coursecommunicator.CourseFeatureCommunicator
import com.eniskaner.coursecommunicator.CourseListQualifierForSignUpScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private val binding: FragmentSignUpBinding by viewBinding(FragmentSignUpBinding::bind)

    @Inject
    lateinit var navController: NavController

    private lateinit var auth: FirebaseAuth

    @Inject
    @CourseListQualifierForSignUpScreen
    lateinit var courseListCommunicator: CourseFeatureCommunicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        confirmPasswordFocusListener()
        setUpUserInteractions()
    }

    private fun setUpUserInteractions() = with(binding) {
        btnAction.setOnClickListener {
            signUpClicked(
                signUpEmail.text.toString().trim(),
                signUpPassword.text.toString().trim()
            )
        }
    }

    private fun signUp(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                courseListCommunicator.launchCourseFeature(
                    CourseFeatureCommunicator.CourseFeatureArgs(
                        CourseAuthNavGraph.ROUTE
                    )
                )
            }
            .addOnFailureListener { exception ->
                Timber.tag(FIREBASE_TAG).e(getString(R.string.error_firebase), exception.message)
            }
    }

    private fun signUpClicked(email: String, password: String) = with(binding) {
        val emailHelperText = textFieldEmail.helperText
        val passwordHelperText = textFieldPassword.helperText
        val confirmPasswordHelperText = textFieldConfirmPassword.helperText
        if (emailHelperText == null && passwordHelperText == null && confirmPasswordHelperText == null) {
            signUp(
                email = email,
                password = password
            )
        }
    }

    private fun confirmPasswordFocusListener() = with(binding) {
        signUpConfirmPassword.addTextChangedListener(
            ConfirmPasswordTextWatcher(
                passwordEditText = signUpPassword,
                confirmPasswordEditText = signUpConfirmPassword,
                textInputLayout = textFieldConfirmPassword,
                validation = { password, confirmPassword ->
                    if (password != confirmPassword) CONFIRM_PASSWORD_ERROR else null
                }
            )
        )
    }
}
