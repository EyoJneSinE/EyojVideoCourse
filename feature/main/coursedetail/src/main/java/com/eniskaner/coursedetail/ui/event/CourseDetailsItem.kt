package com.eniskaner.coursedetail.ui.event

import com.eniskaner.domain.model.CourseDetailUIModel
import com.eniskaner.domain.model.LessonUIModel

sealed class CourseDetailsItem {
    data class CourseDetailItem(val courseDetail: CourseDetailUIModel) : CourseDetailsItem()
    data class CourseLessons(val course: LessonUIModel) : CourseDetailsItem()
}
