package com.example.data.repository

import com.example.data.ApiService
import com.example.data.BuildConfig
import com.example.data.data.mappers.toItem
import com.example.domain.repository.UsersRepository
import com.example.domain.util.Failure
import com.example.domain.util.Loading
import com.example.domain.util.NetworkResult
import com.example.domain.util.Success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UsersRepository {

    override fun getUsers(
        defaultErrorMessage: String
    ): Flow<NetworkResult> = flow {
        emit(Loading)
        try {
            emit(
                Success(
                    users = apiService.getUsers()
                        .body()!!.items.map { itemDto -> itemDto.toItem() })
            )
        } catch (e: Exception) {
            if (BuildConfig.DEBUG) e.printStackTrace()
            emit(Failure(message = defaultErrorMessage))
        }
    }
}