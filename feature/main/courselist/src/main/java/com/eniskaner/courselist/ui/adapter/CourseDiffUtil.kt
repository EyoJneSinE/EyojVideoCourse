package com.eniskaner.courselist.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.eniskaner.courselist.ui.event.CourseListItem

class CourseDiffUtil : DiffUtil.ItemCallback<CourseListItem>() {

    override fun areItemsTheSame(
        oldItem: CourseListItem,
        newItem: CourseListItem
    ): Boolean = when {
        oldItem is CourseListItem.CategoryItem && newItem is CourseListItem.CategoryItem -> {
            oldItem.category.id == newItem.category.id ||
                    oldItem.category.name == newItem.category.name ||
                    oldItem.category.iconUrl == newItem.category.iconUrl ||
                    oldItem.category.color == newItem.category.color
        }

        oldItem is CourseListItem.CourseItem && newItem is CourseListItem.CourseItem -> {
            oldItem.course.id == newItem.course.id ||
                    oldItem.course.price == newItem.course.price ||
                    oldItem.course.title == newItem.course.title ||
                    oldItem.course.categoryId == newItem.course.categoryId ||
                    oldItem.course.thumbnailUrl == newItem.course.thumbnailUrl ||
                    oldItem.course.description == newItem.course.description
        }

        else -> false
    }

    override fun areContentsTheSame(
        oldItem: CourseListItem,
        newItem: CourseListItem
    ): Boolean = oldItem == newItem
}
