package com.eniskaner.coursedetail.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.common.preferences.PreferencesManager
import com.eniskaner.coursedetail.databinding.ItemCourseDetailBinding
import com.eniskaner.coursedetail.databinding.ItemLessonVideoBinding
import com.eniskaner.coursedetail.ui.event.CourseDetailViewType
import com.eniskaner.coursedetail.ui.event.CourseDetailsItem
import com.eniskaner.coursedetail.ui.viewholder.CourseDetailViewHolder
import com.eniskaner.coursedetail.ui.viewholder.LessonVideoViewHolder

class CourseDetailListAdapter(
    private val enrollClickListener: EnrollClickListener,
    private val preferencesManager: PreferencesManager
) : ListAdapter<CourseDetailsItem, RecyclerView.ViewHolder>(CourseDetailDiffUtil()) {

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is CourseDetailsItem.CourseDetailItem -> CourseDetailViewType.COURSE_DETAIL.ordinal
        is CourseDetailsItem.CourseLessons -> CourseDetailViewType.LESSONS.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            CourseDetailViewType.COURSE_DETAIL.ordinal -> {
                val binding = ItemCourseDetailBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                CourseDetailViewHolder(
                    binding = binding,
                    enrollClickListener = enrollClickListener,
                    preferencesManager = preferencesManager
                )
            }

            CourseDetailViewType.LESSONS.ordinal -> {
                val binding = ItemLessonVideoBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                LessonVideoViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CourseDetailViewHolder -> {
                val item = getItem(position) as CourseDetailsItem.CourseDetailItem
                holder.bind(item.courseDetail)
            }

            is LessonVideoViewHolder -> {
                val item = getItem(position) as CourseDetailsItem.CourseLessons
                holder.bind(item.course)
            }
        }
    }
}
