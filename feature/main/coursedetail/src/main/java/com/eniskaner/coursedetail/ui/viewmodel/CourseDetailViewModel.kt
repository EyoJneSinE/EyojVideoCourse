package com.eniskaner.coursedetail.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eniskaner.common.util.Constants.ErrorMessages.DEFAULT_ERROR_MESSAGE
import com.eniskaner.common.util.Resource
import com.eniskaner.coursecommunicator.CourseFeatureCommunicator
import com.eniskaner.coursedetail.ui.state.CourseDetailUIState
import com.eniskaner.domain.usecase.GetCourseDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CourseDetailViewModel @Inject constructor(
    private val getCourseDetailUseCase: GetCourseDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _courseDetailUIState = MutableStateFlow(CourseDetailUIState())
    val courseDetailUIState = _courseDetailUIState.asStateFlow()

    init {
        val courseFeatureArgs = savedStateHandle.get<CourseFeatureCommunicator.CourseFeatureArgs>(
            CourseFeatureCommunicator.COURSE_FEATURE_NAV_KEY
        )

        courseFeatureArgs?.courseId?.let { id ->
            getCourseDetail(id)
        }
    }

    private fun getCourseDetail(id: Int) {
        viewModelScope.launch {
            getCourseDetailUseCase.invoke(id = id).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _courseDetailUIState.update {
                            it.copy(
                                courseDetail = resource.data,
                                courseLessons = resource.data?.lessons ?: emptyList(),
                                isLoading = false
                            )
                        }
                    }

                    is Resource.Error -> {
                        _courseDetailUIState.update {
                            it.copy(
                                error = resource.message ?: DEFAULT_ERROR_MESSAGE,
                                isLoading = false
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _courseDetailUIState.update { it.copy(isLoading = true) }
                    }
                }
            }
        }
    }
}
