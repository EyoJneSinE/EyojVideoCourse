package com.eniskaner.data.di

import com.eniskaner.data.repository.CourseRepositoryImpl
import com.eniskaner.domain.repository.CourseRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CourseRepositoryModule {

    @Binds
    abstract fun bindCourseRepository(courseRepositoryImpl: CourseRepositoryImpl): CourseRepository
}