package com.eniskaner.common.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.eniskaner.common.util.Constants.PreferenceKeys.COURSE_APP_PREFERENCES
import com.eniskaner.common.util.Constants.PreferenceKeys.COURSE_ONBOARDING_KEY_PREFS
import com.eniskaner.common.util.Constants.PreferenceKeys.DARK_MODE_KEY
import com.eniskaner.common.util.Constants.PreferenceKeys.PURCHASED_COURSES_KEY

class PreferencesManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(COURSE_APP_PREFERENCES, Context.MODE_PRIVATE)

    var isCourseOnBoardingCompleted: Boolean
        get() = sharedPreferences.getBoolean(COURSE_ONBOARDING_KEY_PREFS, false)
        set(value) = sharedPreferences.edit {
            putBoolean(COURSE_ONBOARDING_KEY_PREFS, value)
        }

    var purchasedCourseIds: Set<String>
        get() = sharedPreferences.getStringSet(PURCHASED_COURSES_KEY, emptySet()) ?: emptySet()
        set(value) = sharedPreferences.edit {
            putStringSet(PURCHASED_COURSES_KEY, value)
        }

    var isDarkMode: Boolean
        get() = sharedPreferences.getBoolean(DARK_MODE_KEY, false)
        set(value) = sharedPreferences.edit().putBoolean(DARK_MODE_KEY, value).apply()

    fun saveVideoProgress(lessonId: Int, progress: Long) {
        sharedPreferences.edit().putLong("video_progress_$lessonId", progress).apply()
    }

    fun getVideoProgress(lessonId: Int): Long {
        return sharedPreferences.getLong("video_progress_$lessonId", 0L)
    }
}
