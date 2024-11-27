package com.eniskaner.auth.ui.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.eniskaner.auth.R
import com.eniskaner.auth.databinding.FragmentSignInBinding
import com.eniskaner.auth.navigation.CourseAuthNavGraph
import com.eniskaner.auth.ui.util.ValidationTextWatcher
import com.eniskaner.common.util.Constants.FirebaseConstants.FIREBASE_TAG
import com.eniskaner.common.util.Constants.FirebaseConstants.NEED_PASSWORD
import com.eniskaner.common.util.viewBinding
import com.eniskaner.coursecommunicator.CourseFeatureCommunicator
import com.eniskaner.coursecommunicator.CourseListQualifierForSignInScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private val binding: FragmentSignInBinding by viewBinding(FragmentSignInBinding::bind)

    private lateinit var auth: FirebaseAuth

    @Inject
    @CourseListQualifierForSignInScreen
    lateinit var courseListCommunicator: CourseFeatureCommunicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPasswordValidation()
        setUpUserInteractions()
    }

    private fun setUpUserInteractions() = with(binding) {
        btnAction.setOnClickListener {
            signInClicked(
                signInEmail.text.toString().trim(),
                signInPassword.text.toString().trim()
            )
        }
    }

    private fun signInClicked(email: String, password: String) = with(binding) {
        val emailHelperText = textFieldEmail.helperText
        val passwordHelperText = textFieldPassword.helperText
        if (emailHelperText == null && passwordHelperText == null) {
            signIn(
                email = email,
                password = password
            )
        }
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
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

    private fun setupPasswordValidation() {
        binding.signInPassword.addTextChangedListener(
            ValidationTextWatcher(
                editText = binding.signInPassword,
                textInputLayout = binding.textFieldPassword,
                validation = { input ->
                    if (input.isEmpty() || input.isBlank()) NEED_PASSWORD else null
                }
            )
        )
    }
}
