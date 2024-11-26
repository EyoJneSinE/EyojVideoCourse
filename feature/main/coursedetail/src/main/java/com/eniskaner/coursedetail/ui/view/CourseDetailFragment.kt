package com.eniskaner.coursedetail.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.eniskaner.common.util.parcelable
import com.eniskaner.common.util.viewBinding
import com.eniskaner.coursecommunicator.CourseFeatureCommunicator
import com.eniskaner.coursedetail.R
import com.eniskaner.coursedetail.databinding.FragmentCourseDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CourseDetailFragment : Fragment(R.layout.fragment_course_detail) {

    private val binding: FragmentCourseDetailBinding by viewBinding(FragmentCourseDetailBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments?.parcelable(CourseFeatureCommunicator.COURSE_FEATURE_NAV_KEY) as? CourseFeatureCommunicator.CourseFeatureArgs
        val id = args?.courseId
        binding.tvCourseDetail.text = id.toString()
    }
}