package com.example.testcleanarch.domain.usecases

import com.example.testcleanarch.domain.models.Latest
import com.example.testcleanarch.domain.repository.UserRepository


class GetAllLatestUseCase(private val userRepository: UserRepository) {
    suspend fun execute():Latest{
        return userRepository.getAllLatest()
    }
}