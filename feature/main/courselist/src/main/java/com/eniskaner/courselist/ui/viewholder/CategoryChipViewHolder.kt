package com.eniskaner.courselist.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.courselist.R
import com.eniskaner.courselist.databinding.ItemChipBinding
import com.eniskaner.courselist.ui.adapter.CategoryClickListener
import com.eniskaner.courselist.ui.event.CourseListItem
import com.eniskaner.courselist.ui.util.toggleSelection

class CategoryChipViewHolder(
    val binding: ItemChipBinding,
    val categoryClickListener: CategoryClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bindCourseItem(item: CourseListItem.CategoryItem) = with(binding) {
        bCategory.text = item.category.name
        bCategory.setOnClickListener {
            bCategory.toggleSelection(
                selectedDrawable = R.drawable.btn_eliptic_selected,
                defaultDrawable = R.drawable.btn_eliptic
            )
            categoryClickListener.bCategoryClicked(item.category.id)
        }
    }
}
