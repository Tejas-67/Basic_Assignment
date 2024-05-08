package com.tejas.basic_assignment.di

import com.tejas.basic_assignment.presentation.homescreen.HomeScreenViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {
    @Provides
    @Singleton
    fun provideHomeScreenViewModel(): HomeScreenViewModel{
        return HomeScreenViewModel()
    }
}