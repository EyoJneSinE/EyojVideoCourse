package com.eniskaner.data.datasource

import com.eniskaner.data.room.entity.CategoryEntity
import com.eniskaner.data.room.entity.CourseDetailEntity
import com.eniskaner.data.room.entity.CourseEntity
import com.eniskaner.data.room.entity.LessonEntity

interface CourseLocalDataSource {

    suspend fun getCourseList(): List<CourseEntity>

    suspend fun saveCourseList(courseList: List<CourseEntity>)

    suspend fun getCourseDetail(courseId: Int): CourseDetailEntity?

    suspend fun saveCourseDetail(courseDetail: CourseDetailEntity)

    suspend fun getCourseListByCategory(categoryId: Int): List<CourseEntity>

    suspend fun getCourseListBySearchQuery(query: String): List<CourseEntity>

    suspend fun getCourseCategories(): List<CategoryEntity>

    suspend fun saveCourseCategories(courseCategories: List<CategoryEntity>)

    suspend fun getCourseLesson(lessonId: Int): List<LessonEntity>

    suspend fun saveCourseLesson(courseLesson: List<LessonEntity>)

}
