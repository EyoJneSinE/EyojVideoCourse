package com.eniskaner.domain.usecase

import com.eniskaner.domain.repository.CourseRepository
import javax.inject.Inject

class GetCourseLessonUseCase @Inject constructor(
    private val courseRepository: CourseRepository
) {
    suspend operator fun invoke(lessonId: Int) =
        courseRepository.getCourseLesson(lessonId)
}
