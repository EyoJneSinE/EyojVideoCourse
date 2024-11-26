package com.eniskaner.courselist.ui.state

import com.eniskaner.domain.model.CategoryUIModel
import com.eniskaner.domain.model.CourseUIModel

data class CourseListUIState(
    val courses: List<CourseUIModel> = emptyList(),
    val categories: List<CategoryUIModel> = emptyList(),
    val filteredCourses: List<CourseUIModel> = emptyList(),
    val searchResults: List<CourseUIModel> = emptyList(),
    val selectedCategoryId: Int? = null,
    val isLoading: Boolean = false,
    val error: String? = "",
    val query: String = ""
)
