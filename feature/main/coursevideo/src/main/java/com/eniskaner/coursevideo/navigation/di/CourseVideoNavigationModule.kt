package com.eniskaner.coursevideo.navigation.di

import com.eniskaner.coursevideo.navigation.CourseVideoNavGraph
import com.eniskaner.navigationcourseapp.NavigationGraph
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
interface CourseVideoNavigationModule {

    @IntoSet
    @Binds
    fun bindCourseDetailNavigation(
        courseVideoNavGraph: CourseVideoNavGraph
    ): NavigationGraph
}