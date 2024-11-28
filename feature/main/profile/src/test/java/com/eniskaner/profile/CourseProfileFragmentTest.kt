package com.eniskaner.profile

import androidx.appcompat.app.AppCompatDelegate
import com.eniskaner.common.preferences.PreferencesManager
import com.eniskaner.profile.ui.view.CourseProfileFragment
import com.eniskaner.profile.ui.view.ThemeUtils
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CourseProfileFragmentTest {

    @Mock
    private lateinit var mockPreferencesManager: PreferencesManager

    private lateinit var fragment: CourseProfileFragment

    @Before
    fun setUp() {
        fragment = CourseProfileFragment()
        fragment.preferencesManager = mockPreferencesManager
    }

    @Test
    fun `toggleDarkMode enables dark mode when true`() {
        ThemeUtils.toggleDarkMode(true)

        assertThat(AppCompatDelegate.getDefaultNightMode()).isEqualTo(AppCompatDelegate.MODE_NIGHT_YES)
    }

    @Test
    fun `toggleDarkMode disables dark mode when false`() {
        ThemeUtils.toggleDarkMode(false)

        assertThat(AppCompatDelegate.getDefaultNightMode()).isEqualTo(AppCompatDelegate.MODE_NIGHT_NO)
    }
}