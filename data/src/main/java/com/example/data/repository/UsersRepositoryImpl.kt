package com.example.data.repository

import com.example.data.ApiService
import com.example.data.BuildConfig
import com.example.data.data.mappers.toItem
import com.example.domain.data.remote.Item
import com.example.domain.repository.UsersRepository
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UsersRepository {

    override suspend fun getUsers(): List<Item> =
        try {
            val response = apiService.getUsers()
            when (response.isSuccessful && response.body() != null) {
                true -> response.body()?.items?.map { itemDto -> itemDto.toItem() } ?: emptyList()
                false -> emptyList()
            }
        } catch (e: Exception) {
            if (BuildConfig.DEBUG) e.printStackTrace()
            emptyList()
        }
}