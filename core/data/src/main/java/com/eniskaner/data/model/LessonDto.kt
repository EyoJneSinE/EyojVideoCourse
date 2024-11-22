package com.eniskaner.data.model

import kotlinx.serialization.SerialName

data class LessonDto(
    @SerialName("lesson_id") val lessonId: Int,
    val title: String,
    val duration: String,
    @SerialName("video_url") val videoUrl: String
)
