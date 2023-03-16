package com.example.domain.util

import com.example.domain.data.remote.Item

sealed class NetworkResult {

    data class Success(
        val users: List<Item>
    ) : NetworkResult()

    data class Failure(
        val networkFailureType: NetworkFailureType
    ) : NetworkResult()

    object Loading
        : NetworkResult()
}