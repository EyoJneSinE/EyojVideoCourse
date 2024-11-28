package com.eniskaner.auth.ui.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout

class ValidationTextWatcher(
    private val editText: EditText,
    private val textInputLayout: TextInputLayout,
    private val validation: (String) -> String?
) : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable?) {
        val inputText = editText.text.toString().trim()
        textInputLayout.helperText = validation(inputText)
    }
}
