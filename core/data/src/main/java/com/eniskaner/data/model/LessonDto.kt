package com.eniskaner.data.model

import com.eniskaner.common.util.Constants.DatabaseObjectConstants.LESSON_ID
import com.eniskaner.common.util.Constants.DatabaseObjectConstants.LESSON_VIDEO_IMAGE
import com.eniskaner.common.util.Constants.DatabaseObjectConstants.LESSON_VIDEO_URL
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LessonDto(
    @SerialName(LESSON_ID) val lessonId: Int,
    val title: String,
    val duration: String,
    @SerialName(LESSON_VIDEO_URL) val videoUrl: String,
    @SerialName(LESSON_VIDEO_IMAGE) val videoImage: String
)
