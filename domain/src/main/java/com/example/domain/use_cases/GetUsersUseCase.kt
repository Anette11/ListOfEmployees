package com.example.domain.use_cases

import com.example.domain.data.remote.Item
import com.example.domain.repository.UsersRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {
    suspend operator fun invoke(): List<Item> =
        usersRepository.getUsers()
}