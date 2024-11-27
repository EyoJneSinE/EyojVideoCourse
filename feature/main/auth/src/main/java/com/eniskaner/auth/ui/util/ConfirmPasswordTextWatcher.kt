package com.eniskaner.auth.ui.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout

class ConfirmPasswordTextWatcher(
    private val passwordEditText: EditText,
    private val confirmPasswordEditText: EditText,
    private val textInputLayout: TextInputLayout,
    private val validation: (String, String) -> String?
) : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable?) {
        val password = passwordEditText.text.toString().trim()
        val confirmPassword = confirmPasswordEditText.text.toString().trim()
        textInputLayout.error = validation(password, confirmPassword)
    }
}