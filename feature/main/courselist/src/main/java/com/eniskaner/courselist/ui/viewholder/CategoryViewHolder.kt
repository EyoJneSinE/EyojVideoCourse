package com.eniskaner.courselist.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.courselist.databinding.CategoryListBinding
import com.eniskaner.courselist.ui.adapter.CategoryClickListener
import com.eniskaner.courselist.ui.adapter.CategoryListAdapter
import com.eniskaner.courselist.ui.event.CourseListItem

class CategoryViewHolder(
    private val binding: CategoryListBinding,
    private val categoryClickListener: CategoryClickListener
) : RecyclerView.ViewHolder(binding.root) {

    private val categoryListAdapter = CategoryListAdapter(categoryClickListener)

    init {
        binding.rvCategories.adapter = categoryListAdapter
    }

    fun bindCategoryItem(item: CourseListItem.CategoryList) {
        categoryListAdapter.submitList(item.categories)
    }
}
