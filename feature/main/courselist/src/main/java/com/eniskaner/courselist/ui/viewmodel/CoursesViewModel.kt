package com.eniskaner.courselist.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eniskaner.common.util.Resource
import com.eniskaner.courselist.ui.event.CourseEvent
import com.eniskaner.courselist.ui.state.CourseListUIState
import com.eniskaner.domain.usecase.GetCourseCategoryUseCase
import com.eniskaner.domain.usecase.GetCourseListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoursesViewModel @Inject constructor(
    private val getCourseListUseCase: GetCourseListUseCase,
    private val getCourseCategoryUseCase: GetCourseCategoryUseCase
) : ViewModel() {

    private val _courseListUIState = MutableStateFlow(CourseListUIState())
    val courseListUIState = _courseListUIState.asStateFlow()

    init {
        getCourseList()
        getCourseCategory()
    }

    fun onEvent(event: CourseEvent) {
        when (event) {
            is CourseEvent.SearchCourses -> {
                _courseListUIState.update {
                    it.copy(query = event.query)
                }
                updateFilteredCourses()
            }

            is CourseEvent.FilterByCategory -> {
                _courseListUIState.update {
                    it.copy(selectedCategoryId = event.categoryId)
                }
                updateFilteredCourses()
            }

            is CourseEvent.ClearFilters -> {
                _courseListUIState.update {
                    it.copy(
                        query = "",
                        selectedCategoryId = null,
                        filteredCourses = it.courses
                    )
                }
            }
        }
    }

    private fun updateFilteredCourses() {
        _courseListUIState.update { state ->
            val filteredList = state.courses.filter { course ->
                val matchesCategory =
                    state.selectedCategoryId?.let { course.categoryId == it } ?: true
                val matchesQuery = state.query.isBlank() || course.title.contains(
                    state.query.trim(),
                    ignoreCase = true
                )
                matchesCategory && matchesQuery
            }
            state.copy(filteredCourses = filteredList)
        }
    }

    private fun getCourseList() {
        viewModelScope.launch {
            getCourseListUseCase.invoke().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _courseListUIState.update {
                            it.copy(
                                courses = resource.data ?: emptyList(),
                                filteredCourses = resource.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                    }

                    is Resource.Error -> {
                        _courseListUIState.update {
                            it.copy(
                                error = resource.message ?: "Error loading courses",
                                isLoading = false
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _courseListUIState.update { it.copy(isLoading = true) }
                    }
                }
            }
        }
    }

    private fun getCourseCategory() {
        viewModelScope.launch {
            getCourseCategoryUseCase.invoke().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _courseListUIState.update {
                            it.copy(categories = resource.data ?: emptyList(), isLoading = false)
                        }
                    }

                    is Resource.Error -> {
                        _courseListUIState.update {
                            it.copy(
                                error = resource.message ?: "Error loading categories",
                                isLoading = false
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _courseListUIState.update { it.copy(isLoading = true) }
                    }
                }
            }
        }
    }
}
