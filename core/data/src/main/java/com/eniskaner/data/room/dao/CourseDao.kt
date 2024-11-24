package com.eniskaner.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eniskaner.data.room.entity.CategoryEntity
import com.eniskaner.data.room.entity.CourseDetailEntity
import com.eniskaner.data.room.entity.CourseEntity
import com.eniskaner.data.room.entity.LessonEntity

@Dao
interface CourseDao {

    @Query("SELECT * FROM course where category_id = :categoryId")
    suspend fun getCourseListByCategory(categoryId: Int): List<CourseEntity>

    @Query("SELECT * FROM course WHERE title LIKE '%' || :query || '%'")
    suspend fun getCourseListBySearchQuery(query: String): List<CourseEntity>

    @Query("SELECT * FROM course")
    suspend fun getCourseList(): List<CourseEntity>

    @Query("SELECT * FROM course WHERE id = :courseId")
    suspend fun getCourseDetail(courseId: Int): CourseDetailEntity?

    @Query("SELECT * FROM category")
    suspend fun getCourseCategories(): List<CategoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCourseList(courseList: List<CourseEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCourseDetail(courseDetail: CourseDetailEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCourseCategories(courseCategories: List<CategoryEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveLesson(lesson: List<LessonEntity>)

    @Query("SELECT * FROM lesson WHERE lessonId = :lessonId")
    suspend fun getCourseLesson(lessonId: Int): List<LessonEntity>

}
