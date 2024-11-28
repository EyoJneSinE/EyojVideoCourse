package com.eniskaner.courselist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.common.util.Constants.InvalidTypeConstants.INVALID_TYPE_MESSAGE
import com.eniskaner.courselist.databinding.CategoryListBinding
import com.eniskaner.courselist.databinding.ItemCourseBinding
import com.eniskaner.courselist.ui.event.CourseListItem
import com.eniskaner.courselist.ui.util.CourseListViewType
import com.eniskaner.courselist.ui.viewholder.CategoryViewHolder
import com.eniskaner.courselist.ui.viewholder.CourseListViewHolder

class CourseListAdapter(
    private val courseClickListener: CourseClickListener,
    private val categoryClickListener: CategoryClickListener
) : ListAdapter<CourseListItem, RecyclerView.ViewHolder>(CourseDiffUtil()) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is CourseListItem.CategoryItem -> CourseListViewType.CATEGORY.ordinal
            is CourseListItem.CourseItem -> CourseListViewType.COURSE_LIST.ordinal
            is CourseListItem.CategoryList -> CourseListViewType.CATEGORY.ordinal
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            CourseListViewType.CATEGORY.ordinal -> {
                val binding = CategoryListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                CategoryViewHolder(binding, categoryClickListener)
            }

            CourseListViewType.CATEGORY_LIST.ordinal -> {
                val binding = CategoryListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                CategoryViewHolder(binding, categoryClickListener)
            }

            CourseListViewType.COURSE_LIST.ordinal -> {
                val binding = ItemCourseBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                CourseListViewHolder(binding, courseClickListener)
            }

            else -> throw IllegalArgumentException(INVALID_TYPE_MESSAGE)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CategoryViewHolder -> {
                val item = getItem(position)
                if (item is CourseListItem.CategoryList) {
                    holder.bindCategoryItem(item)
                }
            }

            is CourseListViewHolder -> {
                val item = getItem(position) as CourseListItem.CourseItem
                holder.bindCourseItem(item.course)
            }
        }
    }
}
