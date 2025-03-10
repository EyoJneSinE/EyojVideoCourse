package com.eniskaner.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.eniskaner.common.util.Constants.DatabaseObjectConstants.LESSON_TABLE_NAME

@Entity(tableName = LESSON_TABLE_NAME)
data class LessonEntity(
    val lessonCourseId: Int,
    @PrimaryKey val lessonId: Int,
    val title: String,
    val duration: Long,
    val videoUrl: String,
    val videoImage: String
)
