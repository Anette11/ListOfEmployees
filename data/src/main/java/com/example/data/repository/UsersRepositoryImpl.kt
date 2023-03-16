package com.example.data.repository

import com.example.data.ApiService
import com.example.data.BuildConfig
import com.example.data.data.mappers.toItem
import com.example.domain.repository.UsersRepository
import com.example.domain.util.NetworkFailureType
import com.example.domain.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UsersRepository {

    override fun getUsers(): Flow<NetworkResult> = flow {
        emit(NetworkResult.Loading)
        try {
            emit(
                NetworkResult.Success(
                    users = apiService.getUsers()
                        .body()!!.items.map { itemDto -> itemDto.toItem() })
            )
        } catch (e: IOException) {
            if (BuildConfig.DEBUG) e.printStackTrace()
            emit(NetworkResult.Failure(networkFailureType = NetworkFailureType.NetworkConnectionError))
        } catch (e: Exception) {
            if (BuildConfig.DEBUG) e.printStackTrace()
            emit(NetworkResult.Failure(networkFailureType = NetworkFailureType.GenericError))
        }
    }
}