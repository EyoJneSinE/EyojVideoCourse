package com.eniskaner.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.eniskaner.data.model.LessonDto

@Entity(tableName = "course_detail")
data class CourseDetailEntity(
    val id: Int,
    val title: String,
    @ColumnInfo("category_id") val categoryId: Int,
    val description: String,
    val price: String,
    @ColumnInfo("thumbnail_url") val thumbnailUrl: String,
    val lessons: List<LessonDto>
)
