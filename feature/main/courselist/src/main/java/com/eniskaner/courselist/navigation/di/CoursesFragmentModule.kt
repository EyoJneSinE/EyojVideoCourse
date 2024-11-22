package com.eniskaner.courselist.navigation.di

import com.eniskaner.coursecommunicator.CourseDetailQualifier
import com.eniskaner.coursecommunicator.VideoCourseCommunicator
import com.eniskaner.courselist.navigation.CourseFeatureCommunicatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
interface CoursesFragmentModule {

    @Binds
    @CourseDetailQualifier
    fun bindCourseDetailCommunicator(
        courseFeatureCommunicatorImpl: CourseFeatureCommunicatorImpl
    ): VideoCourseCommunicator
}
