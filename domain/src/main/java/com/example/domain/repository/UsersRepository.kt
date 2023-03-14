package com.example.domain.repository

import com.example.domain.data.remote.Item

interface UsersRepository {

    suspend fun getUsers(): List<Item>
}