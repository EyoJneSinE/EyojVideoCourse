package com.eniskaner.coursedetail.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.eniskaner.coursedetail.ui.event.CourseDetailsItem

class CourseDetailDiffUtil : DiffUtil.ItemCallback<CourseDetailsItem>() {
    override fun areItemsTheSame(
        oldItem: CourseDetailsItem,
        newItem: CourseDetailsItem
    ): Boolean = when {
        oldItem is CourseDetailsItem.CourseDetailItem && newItem is CourseDetailsItem.CourseDetailItem -> {
            oldItem.courseDetail == newItem.courseDetail
        }

        oldItem is CourseDetailsItem.CourseLessons && newItem is CourseDetailsItem.CourseLessons -> {
            oldItem.course == newItem.course
        }

        else -> false
    }

    override fun areContentsTheSame(
        oldItem: CourseDetailsItem,
        newItem: CourseDetailsItem
    ): Boolean = oldItem == newItem
}
