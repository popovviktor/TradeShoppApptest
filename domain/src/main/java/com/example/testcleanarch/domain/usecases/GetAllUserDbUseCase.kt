package com.example.testcleanarch.domain.usecases

import com.example.testcleanarch.domain.models.UserModelDomain
import com.example.testcleanarch.domain.repository.UserRepository

class GetAllUserDbUseCase(private val userRepository: UserRepository) {
    suspend fun execute():List<UserModelDomain>{
        return userRepository.getAllDbUsers()
    }
}