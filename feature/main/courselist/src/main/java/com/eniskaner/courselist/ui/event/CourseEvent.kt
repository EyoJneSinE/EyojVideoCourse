package com.eniskaner.courselist.ui.event

sealed class CourseEvent {

    data class SearchCourses(val query: String) : CourseEvent()
    data class FilterByCategory(val categoryId: Int?) : CourseEvent()
    data object ClearFilters : CourseEvent()
}
