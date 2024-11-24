package com.eniskaner.domain.usecase

import com.eniskaner.domain.repository.CourseRepository
import javax.inject.Inject

class GetCourseDetailUseCase @Inject constructor(
    private val courseRepository: CourseRepository
) {
    suspend operator fun invoke(id: Int) =
        courseRepository.getCourseDetail(id = id)
}
