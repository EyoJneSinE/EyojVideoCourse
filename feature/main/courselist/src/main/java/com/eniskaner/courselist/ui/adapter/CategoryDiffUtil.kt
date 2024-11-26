package com.eniskaner.courselist.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.eniskaner.courselist.ui.event.CourseListItem

class CategoryDiffUtil : DiffUtil.ItemCallback<CourseListItem.CategoryItem>() {

    override fun areItemsTheSame(
        oldItem: CourseListItem.CategoryItem,
        newItem: CourseListItem.CategoryItem
    ): Boolean = oldItem.category == newItem.category

    override fun areContentsTheSame(
        oldItem: CourseListItem.CategoryItem,
        newItem: CourseListItem.CategoryItem
    ): Boolean = oldItem == newItem
}
