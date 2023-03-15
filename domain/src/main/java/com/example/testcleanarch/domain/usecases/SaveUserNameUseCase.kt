package com.example.testcleanarch.domain.usecases

import com.example.testcleanarch.domain.models.UserModelDomain
import com.example.testcleanarch.domain.repository.UserRepository

class SaveUserNameUseCase(private val userRepository: UserRepository) {
    fun execute(userModelDomain: UserModelDomain){
        userRepository.saveUserName(userModelDomain)
    }
}