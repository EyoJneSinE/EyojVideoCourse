package com.eniskaner.coursedetail.ui.viewholder

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.common.preferences.PreferencesManager
import com.eniskaner.coursedetail.databinding.ItemCourseDetailBinding
import com.eniskaner.coursedetail.ui.adapter.EnrollClickListener
import com.eniskaner.coursedetail.ui.util.load
import com.eniskaner.domain.model.CourseDetailUIModel

class CourseDetailViewHolder(
    private val binding: ItemCourseDetailBinding,
    private val enrollClickListener: EnrollClickListener,
    private val preferencesManager: PreferencesManager
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(courseDetail: CourseDetailUIModel) = with(binding) {
        tvTitle.text = courseDetail.title
        tvDescription.text = courseDetail.description
        tvCategory.text = courseDetail.categoryName
        tvPrice.text = courseDetail.price
        btnEnroll.setOnClickListener {
            btnEnroll.isVisible =
                !preferencesManager.purchasedCourseIds.contains(courseDetail.id.toString())
            enrollClickListener.enrollClickListener(courseDetail.price, courseDetail.id)
        }
        ivThumbnail.load(courseDetail.thumbnailUrl)
        btnEnroll.isVisible =
            !preferencesManager.purchasedCourseIds.contains(courseDetail.id.toString())
    }
}
