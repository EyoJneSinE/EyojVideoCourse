package com.eniskaner.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.eniskaner.common.util.Constants.DatabaseObjectConstants.COURSE_TABLE_NAME

@Entity(tableName = COURSE_TABLE_NAME)
data class CourseEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val categoryId: Int,
    val categoryName: String,
    val description: String,
    val price: String,
    val thumbnailUrl: String
)
