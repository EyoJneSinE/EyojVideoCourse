package com.eniskaner.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course")
data class CourseEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    @ColumnInfo("category_id") val categoryId: Int,
    val description: String,
    val price: String,
    @ColumnInfo("thumbnail_url") val thumbnailUrl: String
)
