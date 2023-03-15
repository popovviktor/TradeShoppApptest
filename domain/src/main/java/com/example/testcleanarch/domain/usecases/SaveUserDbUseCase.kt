package com.example.testcleanarch.domain.usecases

import com.example.testcleanarch.domain.models.UserModelDomain
import com.example.testcleanarch.domain.repository.UserRepository

class SaveUserDbUseCase(private val userRepository: UserRepository) {
    suspend fun execute(userModelDomain: UserModelDomain){
        userRepository.saveDB(userModelDomain)
    }
}