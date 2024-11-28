package com.eniskaner.profile

import androidx.appcompat.app.AppCompatDelegate
import com.eniskaner.profile.ui.view.ThemeUtils.toggleDarkMode
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ThemeUtilsTest {

    @Mock
    private lateinit var appCompatDelegate: AppCompatDelegate


    @Test
    fun `toggleDarkMode enables dark mode when true`() {
        val enable = true
        toggleDarkMode(enable)

        assertThat(AppCompatDelegate.getDefaultNightMode()).isEqualTo(AppCompatDelegate.MODE_NIGHT_YES)
    }

    @Test
    fun `toggleDarkMode disables dark mode when false`() {
        val enable = false
        toggleDarkMode(enable)

        assertThat(AppCompatDelegate.getDefaultNightMode()).isEqualTo(AppCompatDelegate.MODE_NIGHT_NO)
    }
}