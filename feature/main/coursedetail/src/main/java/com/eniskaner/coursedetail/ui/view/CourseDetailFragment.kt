package com.eniskaner.coursedetail.ui.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.eniskaner.common.preferences.PreferencesManager
import com.eniskaner.common.util.launchAndRepeatWithViewLifecycle
import com.eniskaner.common.util.viewBinding
import com.eniskaner.coursecommunicator.CourseFeatureCommunicator
import com.eniskaner.coursedetail.R
import com.eniskaner.coursedetail.databinding.FragmentCourseDetailBinding
import com.eniskaner.coursedetail.navigation.CourseDetailNavGraph
import com.eniskaner.coursedetail.ui.adapter.CourseDetailListAdapter
import com.eniskaner.coursedetail.ui.adapter.CourseVideoClickListener
import com.eniskaner.coursedetail.ui.adapter.EnrollClickListener
import com.eniskaner.coursedetail.ui.event.CourseDetailsItem
import com.eniskaner.coursedetail.ui.util.PaymentPopupDialog
import com.eniskaner.coursedetail.ui.viewmodel.CourseDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CourseDetailFragment : Fragment(R.layout.fragment_course_detail), EnrollClickListener, CourseVideoClickListener {

    @Inject
    lateinit var preferencesManager: PreferencesManager

    private val binding: FragmentCourseDetailBinding by viewBinding(FragmentCourseDetailBinding::bind)

    private val courseDetailAdapter by lazy {
        CourseDetailListAdapter(
            enrollClickListener = this@CourseDetailFragment,
            preferencesManager = preferencesManager,
            videoClickListener = this@CourseDetailFragment
        )
    }

    private val courseDetailViewModel: CourseDetailViewModel by viewModels()

    @Inject
    lateinit var courseVideoFeatureCommunicator: CourseFeatureCommunicator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        getCourseDetail()
    }

    private fun initRecyclerView() = with(binding.rvCourseDetail) {
        adapter = courseDetailAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }

    private fun getCourseDetail() {
        launchAndRepeatWithViewLifecycle {
            courseDetailViewModel.courseDetailUIState.collect { state ->
                setProgressBar(isLoading = state.isLoading)

                val combineList = mutableListOf<CourseDetailsItem>().apply {
                    state.courseDetail?.lessons
                    state.courseDetail?.let {
                        addAll(listOf(CourseDetailsItem.CourseDetailItem(it)))
                    }
                    val lessonItems =
                        state.courseLessons.map { CourseDetailsItem.CourseLessons(it) }
                    addAll(lessonItems)
                }
                courseDetailAdapter.submitList(combineList)
            }
        }
    }

    private fun setProgressBar(isLoading: Boolean) {
        binding.progressBarCourseDetail.isVisible = isLoading
    }

    override fun enrollClickListener(price: String, id: Int) {
        val paymentDialog = PaymentPopupDialog(requireContext(), price) {
            preferencesManager.purchasedCourseIds =
                preferencesManager.purchasedCourseIds.toMutableSet().apply {
                    add(id.toString())
                }
            Toast.makeText(
                requireContext(),
                getString(R.string.payment_completed_successfully), Toast.LENGTH_SHORT
            ).show()
        }
        paymentDialog.show()
    }

    override fun videoClickListener(videoUrl: String) {
        courseVideoFeatureCommunicator.launchCourseFeature(
            CourseFeatureCommunicator.CourseFeatureArgs(
                previousRoute = CourseDetailNavGraph.ROUTE,
                videoUrl = videoUrl
            )
        )
    }
}
