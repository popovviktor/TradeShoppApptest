package com.example.testcleanarch.domain.usecases

import com.example.testcleanarch.domain.models.ItemOnTouch
import com.example.testcleanarch.domain.repository.UserRepository

class GetItemOnTouchUseCase(private val userRepository: UserRepository) {
    suspend fun execute(): ItemOnTouch {
        return userRepository.getItemOnTouch()
    }
}