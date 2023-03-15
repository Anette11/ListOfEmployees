package com.example.domain.repository

import com.example.domain.util.NetworkResult
import kotlinx.coroutines.flow.Flow

interface UsersRepository {

    fun getUsers(
        defaultErrorMessage: String
    ): Flow<NetworkResult>
}