package com.eniskaner.courselist.ui.event

import com.eniskaner.domain.model.CategoryUIModel
import com.eniskaner.domain.model.CourseUIModel

sealed class CourseListItem {
    data class CategoryItem(val category: CategoryUIModel) : CourseListItem()
    data class CourseItem(val course: CourseUIModel) : CourseListItem()
    data class CategoryList(val categories: List<CategoryItem>) : CourseListItem()
}
