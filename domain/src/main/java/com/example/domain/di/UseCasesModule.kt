package com.example.domain.di

import com.example.domain.repository.UsersRepository
import com.example.domain.use_cases.GetUsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    @Singleton
    fun provideGetUsersUseCase(
        usersRepository: UsersRepository
    ): GetUsersUseCase = GetUsersUseCase(usersRepository = usersRepository)
}