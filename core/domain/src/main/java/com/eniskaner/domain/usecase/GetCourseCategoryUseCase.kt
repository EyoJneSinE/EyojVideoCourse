package com.eniskaner.domain.usecase

import com.eniskaner.domain.repository.CourseRepository
import javax.inject.Inject

class GetCourseCategoryUseCase @Inject constructor(
    private val courseRepository: CourseRepository
) {
    suspend operator fun invoke(categoryId: Int) =
        courseRepository.getCourseCategory(categoryId = categoryId)
}
