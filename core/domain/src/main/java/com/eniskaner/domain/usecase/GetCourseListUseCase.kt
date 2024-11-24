package com.eniskaner.domain.usecase

import com.eniskaner.domain.repository.CourseRepository
import javax.inject.Inject

class GetCourseListUseCase @Inject constructor(
    private val courseRepository: CourseRepository
) {
    suspend operator fun invoke() =
        courseRepository.getCourseList()
}
