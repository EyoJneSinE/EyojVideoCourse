package com.eniskaner.courselist.ui.util

import com.google.android.material.button.MaterialButton

fun MaterialButton.toggleSelection(
    selectedDrawable: Int,
    defaultDrawable: Int
) {
    isSelected = !isSelected
    setBackgroundResource(if (isSelected) selectedDrawable else defaultDrawable)
}
