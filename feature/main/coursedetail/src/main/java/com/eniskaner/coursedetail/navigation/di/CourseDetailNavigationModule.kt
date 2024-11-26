package com.eniskaner.coursedetail.navigation.di

import com.eniskaner.coursedetail.navigation.CourseDetailNavGraph
import com.eniskaner.navigationcourseapp.NavigationGraph
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
interface CourseDetailNavigationModule {

    @IntoSet
    @Binds
    fun bindCourseDetailNavigation(
        courseDetailNavGraph: CourseDetailNavGraph
    ): NavigationGraph
}
