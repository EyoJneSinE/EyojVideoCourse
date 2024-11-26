package com.eniskaner.courselist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.eniskaner.courselist.databinding.ItemChipBinding
import com.eniskaner.courselist.ui.event.CourseListItem
import com.eniskaner.courselist.ui.viewholder.CategoryChipViewHolder

class CategoryListAdapter(
    private val categoryClickListener: CategoryClickListener
) : ListAdapter<CourseListItem.CategoryItem, CategoryChipViewHolder>(CategoryDiffUtil()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryChipViewHolder {
        val binding = ItemChipBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoryChipViewHolder(
            binding = binding,
            categoryClickListener = categoryClickListener
        )
    }

    override fun onBindViewHolder(holder: CategoryChipViewHolder, position: Int) {
        holder.bindCourseItem(getItem(position) as CourseListItem.CategoryItem)
    }
}
