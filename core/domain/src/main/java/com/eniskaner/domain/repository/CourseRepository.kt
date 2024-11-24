package com.eniskaner.domain.repository

import com.eniskaner.common.util.Resource
import com.eniskaner.domain.model.CategoryUIModel
import com.eniskaner.domain.model.CourseDetailUIModel
import com.eniskaner.domain.model.CourseUIModel
import com.eniskaner.domain.model.LessonUIModel
import kotlinx.coroutines.flow.Flow

interface CourseRepository {

    suspend fun getCourseList(): Flow<Resource<List<CourseUIModel>>>

    suspend fun getCourseDetail(id: Int): Flow<Resource<CourseDetailUIModel?>>

    suspend fun getCourseCategory(categoryId: Int): Flow<Resource<List<CategoryUIModel>>>

    suspend fun getCourseListBySearch(query: String): Flow<Resource<List<CourseUIModel>>>

    suspend fun getCourseLesson(lessonId: Int): Flow<Resource<List<LessonUIModel>>>

}
