package com.example.domain.util

import com.example.domain.data.remote.Item

interface NetworkResult

data class Success(
    val users: List<Item>
) : NetworkResult

data class Failure(
    val message: String
) : NetworkResult

object Loading
    : NetworkResult
