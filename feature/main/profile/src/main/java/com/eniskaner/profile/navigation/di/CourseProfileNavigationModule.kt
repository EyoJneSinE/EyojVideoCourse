package com.eniskaner.profile.navigation.di

import com.eniskaner.auth.navigation.CourseAuthNavGraph
import com.eniskaner.navigationcourseapp.NavigationGraph
import com.eniskaner.profile.navigation.CourseProfileNavGraph
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
interface CourseProfileNavigationModule {

    @IntoSet
    @Binds
    fun bindCourseAuthNavigation(courseProfileNavGraph: CourseProfileNavGraph): NavigationGraph
}