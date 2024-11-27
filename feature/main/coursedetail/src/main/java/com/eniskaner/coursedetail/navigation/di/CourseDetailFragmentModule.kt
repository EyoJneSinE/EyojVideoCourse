package com.eniskaner.coursedetail.navigation.di

import com.eniskaner.coursecommunicator.CourseDetailQualifier
import com.eniskaner.coursecommunicator.CourseFeatureCommunicator
import com.eniskaner.coursedetail.navigation.CourseDetailFeatureCommunicatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
interface CourseDetailFragmentModule {

    @Binds
    @CourseDetailQualifier
    fun bindCourseDetailCommunicator(
        courseDetailCommunicatorImpl: CourseDetailFeatureCommunicatorImpl
    ): CourseFeatureCommunicator
}
