package com.eniskaner.courselist.navigation.di

import com.eniskaner.coursecommunicator.CourseFeatureCommunicator
import com.eniskaner.coursecommunicator.CourseListQualifierForSignInScreen
import com.eniskaner.coursecommunicator.CourseListQualifierForSignUpScreen
import com.eniskaner.courselist.navigation.CourseFeatureCommunicatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
interface CoursesFragmentModule {

    @Binds
    @CourseListQualifierForSignUpScreen
    fun bindCourseDetailCommunicatorForSignUpScreen(
        courseFeatureCommunicatorImpl: CourseFeatureCommunicatorImpl
    ): CourseFeatureCommunicator

    @Binds
    @CourseListQualifierForSignInScreen
    fun bindCourseDetailCommunicatorForSignInScreen(
        courseFeatureCommunicatorImpl: CourseFeatureCommunicatorImpl
    ): CourseFeatureCommunicator
}
