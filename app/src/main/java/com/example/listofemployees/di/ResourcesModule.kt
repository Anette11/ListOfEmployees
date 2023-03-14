package com.example.listofemployees.di

import android.content.Context
import com.example.listofemployees.util.ResourcesProvider
import com.example.listofemployees.util.ResourcesProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ResourcesModule {

    @Provides
    @Singleton
    fun provideResourcesProvider(
        @ApplicationContext context: Context
    ): ResourcesProvider = ResourcesProviderImpl(context = context)
}