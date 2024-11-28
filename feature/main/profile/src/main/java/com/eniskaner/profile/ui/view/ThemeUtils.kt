package com.eniskaner.profile.ui.view

import androidx.appcompat.app.AppCompatDelegate

object ThemeUtils {

    fun toggleDarkMode(enable: Boolean) {
        val mode = if (enable) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        AppCompatDelegate.setDefaultNightMode(mode)
    }
}