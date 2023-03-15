package com.example.testcleanarch.domain.usecases

import com.example.testcleanarch.domain.models.UserModelDomain
import com.example.testcleanarch.domain.repository.UserRepository

class GetUserNameUseCase(private val userRepository: UserRepository) {
    fun execute(): UserModelDomain {
       return userRepository.getUserName()
    }
}