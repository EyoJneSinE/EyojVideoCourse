package com.eniskaner.eyojvideocourse.di

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.eniskaner.eyojvideocourse.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ActivityModule {

    @Provides
    fun provideActivity(@ActivityContext context: Context): NavController {
        val activity = context as FragmentActivity
        val navHostFragment =
            activity.supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        return navHostFragment.navController
    }

}