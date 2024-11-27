package com.eniskaner.coursedetail.ui.state

import com.eniskaner.domain.model.CourseDetailUIModel
import com.eniskaner.domain.model.LessonUIModel

data class CourseDetailUIState(
    val isLoading: Boolean = false,
    val courseDetail: CourseDetailUIModel? = null,
    val courseLessons: List<LessonUIModel> = emptyList(),
    val error: String = ""
)
