package com.eniskaner.domain.repository

import com.eniskaner.common.util.Resource
import com.eniskaner.domain.model.CategoryUIModel
import com.eniskaner.domain.model.CourseUIModel
import kotlinx.coroutines.flow.Flow

interface CourseRepository {

    suspend fun getCourseList(): Flow<Resource<List<CourseUIModel>>>

    suspend fun getCourseDetail(id: Int): Flow<Resource<CourseUIModel?>>

    suspend fun getCourseListByCategory(categoryId: Int): Flow<Resource<List<CategoryUIModel>>>

    suspend fun getCourseListBySearch(query: String): Flow<Resource<List<CourseUIModel>>>

}
