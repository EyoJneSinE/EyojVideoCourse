package com.eniskaner.common.util

object Constants {
    object PreferenceKeys {
        const val COURSE_APP_PREFERENCES = "app_preferences"
        const val COURSE_ONBOARDING_KEY_PREFS = "onboarding_completed"
        const val PURCHASED_COURSES_KEY = "purchased_courses_key"
        const val DARK_MODE_KEY = "DARK_MODE_KEY"
    }

    object Delays {
        const val DEFAULT_DELAY = 1500L
    }

    object InvalidTypeConstants {
        const val INVALID_TYPE_MESSAGE = "Invalid type"
    }

    object AssetConstants {
        const val ALL_COURSE_LIST = "all-courses.json"
        const val COURSE_DETAIL = "course-details.json"
        const val COURSE_CATEGORIES = "course-categories.json"
        const val CATEGORY_TECHNOLOGY = "category-technology.json"
        const val CATEGORY_AI = "category-ai.json"
        const val CATEGORY_DESIGN = "category-design.json"
    }

    object ErrorMessages {
        const val DEFAULT_ERROR_MESSAGE = "An error occurred!"
    }

    object DatabaseObjectConstants {
        const val COURSE_DB_NAME = "courses.db"
        const val COURSE_TABLE_NAME = "course"
        const val CATEGORY_TABLE_NAME = "category"
        const val LESSON_TABLE_NAME = "lesson"
        const val COURSE_DETAIL_TABLE_NAME = "course_detail"
        const val CATEGORY_ICON_URL = "icon_url"
        const val DTO_CATEGORY_ID = "category_id"
        const val DTO_CATEGORY_NAME = "category_name"
        const val DTO_THUMBNAIL_URL = "thumbnail_url"
        const val LESSON_ID = "lesson_id"
        const val LESSON_COURSE_ID = "lesson_course_id"
        const val LESSON_VIDEO_URL = "video_url"
        const val LESSON_VIDEO_IMAGE = "video_image"
    }

    object ExoPlayerConstants {
        const val PLAYBACK_POSITION_KEY = "playback_position"
        const val PLAY_WHEN_READY_KEY = "play_when_ready"
    }

    object FirebaseConstants {
        const val FIREBASE_TAG = "FirebaseAuth"
        const val CONFIRM_PASSWORD_ERROR = "Passwords not the same"
        const val NEED_PASSWORD = "Need Password"
    }
}
