package com.eniskaner.data.di

import com.eniskaner.data.datasource.CourseDataSource
import com.eniskaner.data.datasource.CourseDataSourceImpl
import com.eniskaner.data.datasource.CourseLocalDataSource
import com.eniskaner.data.datasource.CourseLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CourseDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindCourseDataSource(courseDataSourceImpl: CourseDataSourceImpl): CourseDataSource

    @Binds
    @Singleton
    abstract fun bindCourseDetailDataSource(courseLocalDataSourceImpl: CourseLocalDataSourceImpl): CourseLocalDataSource
}