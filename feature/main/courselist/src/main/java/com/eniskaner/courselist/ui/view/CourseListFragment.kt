package com.eniskaner.courselist.ui.view

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.eniskaner.common.util.launchAndRepeatWithViewLifecycle
import com.eniskaner.common.util.viewBinding
import com.eniskaner.coursecommunicator.CourseDetailQualifier
import com.eniskaner.coursecommunicator.CourseFeatureCommunicator
import com.eniskaner.courselist.R
import com.eniskaner.courselist.databinding.FragmentCourseListBinding
import com.eniskaner.courselist.navigation.CoursesNavGraph
import com.eniskaner.courselist.ui.adapter.CategoryClickListener
import com.eniskaner.courselist.ui.adapter.CourseClickListener
import com.eniskaner.courselist.ui.adapter.CourseListAdapter
import com.eniskaner.courselist.ui.event.CourseEvent
import com.eniskaner.courselist.ui.event.CourseListItem
import com.eniskaner.courselist.ui.util.CourseSearchTextWatcher
import com.eniskaner.courselist.ui.viewmodel.CoursesViewModel
import com.eniskaner.domain.model.CourseUIModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CourseListFragment : Fragment(R.layout.fragment_course_list), CourseClickListener,
    CategoryClickListener {

    private val binding: FragmentCourseListBinding by viewBinding(FragmentCourseListBinding::bind)

    @Inject
    @CourseDetailQualifier
    lateinit var courseDetailCommunicator: CourseFeatureCommunicator

    private val courseListAdapter by lazy {
        CourseListAdapter(
            courseClickListener = this@CourseListFragment,
            categoryClickListener = this@CourseListFragment
        )
    }

    private val coursesViewModel: CoursesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        getCourseList()
        setUIWithSearchCourses()
    }

    private fun initRecyclerView() = with(binding.rvCourseList) {
        adapter = courseListAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }

    private fun getCourseList() {
        launchAndRepeatWithViewLifecycle {
            coursesViewModel.courseListUIState.collect { state ->
                setProgressBar(isLoading = state.isLoading)

                val combineList = mutableListOf<CourseListItem>().apply {
                    val categoryItems = state.categories.map { CourseListItem.CategoryItem(it) }
                    val categoryList = CourseListItem.CategoryList(categoryItems)
                    //addAll(categoryItems)
                    addAll(listOf(categoryList))
                    addAll(state.filteredCourses.map { CourseListItem.CourseItem(it) })
                }
                courseListAdapter.submitList(combineList)
            }
        }
    }

    private fun setUIWithSearchCourses() = with(binding) {
        searchView.setOnQueryTextListener(CourseSearchTextWatcher { query ->
            coursesViewModel.onEvent(CourseEvent.SearchCourses(query = query))
        })
    }


    private fun setProgressBar(isLoading: Boolean) {
        binding.progressBarCourseList.isVisible = isLoading
    }

    override fun courseClicked(id: Int) {
        courseDetailCommunicator.launchCourseFeature(
            CourseFeatureCommunicator.CourseFeatureArgs(
                previousRoute = CoursesNavGraph.ROUTE,
                courseId = id
            )
        )
    }

    override fun bCategoryClicked(categoryId: Int) {
        val currentCategoryId = coursesViewModel.courseListUIState.value.selectedCategoryId
        if (currentCategoryId == categoryId) {
            coursesViewModel.onEvent(CourseEvent.ClearFilters)
        } else {
            coursesViewModel.onEvent(CourseEvent.FilterByCategory(categoryId))
        }
    }
}
