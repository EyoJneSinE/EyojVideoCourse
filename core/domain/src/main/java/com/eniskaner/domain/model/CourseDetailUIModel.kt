package com.eniskaner.domain.model

data class CourseDetailUIModel(
    val id: Int,
    val title: String,
    val categoryId: Int,
    val description: String,
    val price: String,
    val thumbnailUrl: String,
    val lessons: List<LessonUIModel>
)
