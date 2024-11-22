package com.eniskaner.data.model

import kotlinx.serialization.SerialName

data class CourseDetailDto(
    val id: Int,
    val title: String,
    @SerialName("category_id") val categoryId: Int,
    val description: String,
    val price: String,
    @SerialName("thumbnail_url") val thumbnailUrl: String,
    val lessons: List<LessonDto>
)
