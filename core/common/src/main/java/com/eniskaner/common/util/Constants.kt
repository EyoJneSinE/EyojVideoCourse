package com.eniskaner.common.util

object Constants {
    object PreferenceKeys {
        const val COURSE_APP_PREFERENCES = "app_preferences"
        const val COURSE_ONBOARDING_KEY_PREFS = "onboarding_completed"
    }

    object Delays {
        const val DEFAULT_DELAY = 1500L
    }

    object DatabaseConstants {
        const val COURSE_DB_NAME = "courses.db"
        const val COURSE_DETAIL_TABLE_NAME = "course_detail"
    }

    object AssetConstants {
        const val ALL_COURSE_LIST = "all_courses.json"
        const val COURSE_DETAIL = "course_details.json"
        const val COURSE_CATEGORIES = "course_categories.json"
        const val CATEGORY_TECHNOLOGY = "category_technology.json"
        const val CATEGORY_AI = "category_ai.json"
        const val CATEGORY_DESIGN = "category_design.json"
    }

    object ErrorMessages {
        const val DEFAULT_ERROR_MESSAGE = "An error occurred!"
    }
}
