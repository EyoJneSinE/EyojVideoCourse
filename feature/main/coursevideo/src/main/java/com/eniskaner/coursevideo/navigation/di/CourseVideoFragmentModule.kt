package com.eniskaner.coursevideo.navigation.di

import com.eniskaner.coursecommunicator.CourseFeatureCommunicator
import com.eniskaner.coursevideo.navigation.CourseVideoCommunicatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
interface CourseVideoFragmentModule {

    @Binds
    fun bindCourseDetailCommunicator(
        courseVideoCommunicatorImpl: CourseVideoCommunicatorImpl
    ): CourseFeatureCommunicator
}