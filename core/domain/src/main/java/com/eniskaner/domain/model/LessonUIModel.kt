package com.eniskaner.domain.model

data class LessonUIModel(
    val lessonCourseId: Int,
    val lessonId: Int,
    val title: String,
    val duration: Long,
    val videoUrl: String,
    val videoImage: String
)
