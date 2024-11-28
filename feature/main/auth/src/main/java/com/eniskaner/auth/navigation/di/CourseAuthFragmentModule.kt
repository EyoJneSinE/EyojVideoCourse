package com.eniskaner.auth.navigation.di

import com.eniskaner.auth.navigation.CourseAuthCommunicatorImpl
import com.eniskaner.coursecommunicator.CourseAuthQualifierForProfileScreen
import com.eniskaner.coursecommunicator.CourseFeatureCommunicator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
interface CourseAuthFragmentModule {

    @Binds
    @CourseAuthQualifierForProfileScreen
    fun bindCourseDetailCommunicatorForSignUpScreen(
        courseAuthCommunicatorImpl: CourseAuthCommunicatorImpl
    ): CourseFeatureCommunicator
}
