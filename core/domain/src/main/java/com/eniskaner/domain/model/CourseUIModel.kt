package com.eniskaner.domain.model

data class CourseUIModel(
    val id: Int,
    val title: String,
    val categoryId: Int,
    val categoryName: String,
    val description: String,
    val price: String,
    val thumbnailUrl: String
)
