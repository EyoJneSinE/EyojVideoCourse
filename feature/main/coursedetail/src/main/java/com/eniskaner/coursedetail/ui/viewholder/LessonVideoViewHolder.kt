package com.eniskaner.coursedetail.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.coursedetail.R
import com.eniskaner.coursedetail.databinding.ItemLessonVideoBinding
import com.eniskaner.coursedetail.ui.adapter.CourseVideoClickListener
import com.eniskaner.coursedetail.ui.util.load
import com.eniskaner.domain.model.LessonUIModel

class LessonVideoViewHolder(
    private val binding: ItemLessonVideoBinding,
    private val videoClickListener: CourseVideoClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(lesson: LessonUIModel) = with(binding) {
        tvVideoTitle.text = lesson.title
        ivVideoThumbnail.load(lesson.videoImage)
        tvVideoDuration.text =
            binding.root.context.getString(R.string.video_duration, lesson.duration)
        ivVideoThumbnail.setOnClickListener {
            videoClickListener.videoClickListener(lesson.videoUrl)
        }
    }
}
