package com.eniskaner.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object CourseDispatcherModule {

    @IoDispatcher
    @Provides
    fun provideDispatcherIo() = Dispatchers.IO

    @MainDispatcher
    @Provides
    fun provideDispatcherMain() = Dispatchers.Main

    @DefaultDispatcher
    @Provides
    fun provideDispatcherDefault() = Dispatchers.Default
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class IoDispatcher

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class MainDispatcher

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class DefaultDispatcher