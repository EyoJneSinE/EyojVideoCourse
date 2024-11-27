package com.eniskaner.auth.navigation.di

import com.eniskaner.auth.navigation.CourseAuthNavGraph
import com.eniskaner.navigationcourseapp.NavigationGraph
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
interface CourseAuthNavigationModule {

    @IntoSet
    @Binds
    fun bindCourseAuthNavigation(courseAuthNavGraph: CourseAuthNavGraph): NavigationGraph
}
