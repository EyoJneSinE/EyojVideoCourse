package com.eniskaner.coursedetail.ui.viewholder

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.common.preferences.PreferencesManager
import com.eniskaner.coursedetail.R
import com.eniskaner.coursedetail.databinding.ItemLessonVideoBinding
import com.eniskaner.coursedetail.ui.adapter.CourseVideoClickListener
import com.eniskaner.coursedetail.ui.util.calculateProgressPercentage
import com.eniskaner.coursedetail.ui.util.formatDuration
import com.eniskaner.coursedetail.ui.util.load
import com.eniskaner.coursedetail.ui.util.updateProgressOverlay
import com.eniskaner.domain.model.LessonUIModel

class LessonVideoViewHolder(
    private val binding: ItemLessonVideoBinding,
    private val videoClickListener: CourseVideoClickListener,
    private val preferencesManager: PreferencesManager
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(lesson: LessonUIModel) = with(binding) {
        val isPurchased = preferencesManager.purchasedCourseIds.contains<String>(lesson.lessonCourseId.toString())
        val savedProgress = preferencesManager.getVideoProgress(lesson.lessonId)

        tvPurchaseRequired.isVisible = !isPurchased

        if (isPurchased) {
            tvVideoTitle.text = lesson.title
            ivVideoThumbnail.load(lesson.videoImage)
            tvVideoDuration.text = lesson.duration.formatDuration()
            ivVideoThumbnail.setOnClickListener {
                videoClickListener.videoClickListener(
                    videoUrl = lesson.videoUrl,
                    lessonId = lesson.lessonId
                )
            }

            val progressPercentage = savedProgress.calculateProgressPercentage(lesson.duration)

            root.updateProgressOverlay(
                R.id.progress_overlay,
                progressPercentage
            )
        }
    }
}
