package com.eniskaner.domain.usecase

import com.eniskaner.domain.repository.CourseRepository
import javax.inject.Inject

class GetCourseListBySearchUseCase @Inject constructor(
    private val courseRepository: CourseRepository
) {
    suspend operator fun invoke(searchQuery: String) =
        courseRepository.getCourseListBySearch(query = searchQuery)
}
