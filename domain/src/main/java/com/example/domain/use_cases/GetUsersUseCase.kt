package com.example.domain.use_cases

import com.example.domain.repository.UsersRepository
import com.example.domain.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {
    operator fun invoke(): Flow<NetworkResult> = usersRepository.getUsers()
}