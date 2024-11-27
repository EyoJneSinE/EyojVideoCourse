package com.eniskaner.courselist.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.coursedetail.ui.util.load
import com.eniskaner.courselist.databinding.ItemCourseBinding
import com.eniskaner.courselist.ui.adapter.CourseClickListener
import com.eniskaner.domain.model.CourseUIModel

class CourseListViewHolder(
    private val binding: ItemCourseBinding,
    private val courseClickListener: CourseClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bindCourseItem(item: CourseUIModel) = with(binding) {
        tvCategory.text = item.categoryName
        tvCourseTitle.text = item.title
        imgThumbnail.load(item.thumbnailUrl)
        cardCourse.setOnClickListener {
            courseClickListener.courseClicked(item.categoryId)
        }
    }
}
