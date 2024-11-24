package com.eniskaner.data.datasource

import com.eniskaner.data.model.CategoryDto
import com.eniskaner.data.model.CourseDetailDto
import com.eniskaner.data.model.CourseDto

interface CourseDataSource {

    suspend fun getCourseList(): List<CourseDto>

    suspend fun getCourseDetail(courseId: Int): CourseDetailDto?

    suspend fun getCourseListByCategory(categoryId: Int): List<CourseDto>

    suspend fun getCourseListBySearchQuery(query: String): List<CourseDto>

    suspend fun getCourseCategories(): List<CategoryDto>

}
