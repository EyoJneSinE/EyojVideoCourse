package com.eniskaner.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eniskaner.data.room.dao.CourseDao
import com.eniskaner.data.room.entity.CategoryEntity
import com.eniskaner.data.room.entity.CourseDetailEntity
import com.eniskaner.data.room.entity.CourseEntity
import com.eniskaner.data.room.entity.LessonEntity

@Database(
    entities = [CourseEntity::class, CourseDetailEntity::class, CategoryEntity::class, LessonEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CourseDatabase : RoomDatabase() {

    abstract fun getCourseDao(): CourseDao
}
