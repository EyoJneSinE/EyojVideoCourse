package com.eniskaner.data.di

import android.content.Context
import android.content.res.AssetManager
import androidx.room.Room
import com.eniskaner.data.room.database.CourseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CourseDataModule {

    @Provides
    @Singleton
    fun provideJson() = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideAssetsManager(@ApplicationContext context: Context): AssetManager = context.assets

    @Provides
    @Singleton
    fun provideCourseDatabase(@ApplicationContext context: Context): CourseDatabase =
        Room.databaseBuilder(
            context,
            CourseDatabase::class.java,
            "course_database"
        ).build()
}
