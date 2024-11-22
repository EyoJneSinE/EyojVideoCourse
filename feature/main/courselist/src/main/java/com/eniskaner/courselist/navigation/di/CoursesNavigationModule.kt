package com.eniskaner.courselist.navigation.di

import com.eniskaner.courselist.navigation.CoursesNavGraph
import com.eniskaner.navigationcourseapp.NavigationGraph
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
interface CoursesNavigationModule {

    @IntoSet
    @Binds
    fun bindCoursesNavigation(coursesNavGraph: CoursesNavGraph): NavigationGraph
}
