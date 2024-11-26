package com.eniskaner.data.room.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.eniskaner.common.util.Constants.DatabaseObjectConstants.COURSE_DETAIL_TABLE_NAME

@Entity(tableName = COURSE_DETAIL_TABLE_NAME)
data class CourseDetailEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val categoryId: Int,
    val description: String,
    val price: String,
    val thumbnailUrl: String,
    val lessons: List<LessonEntity>
)
