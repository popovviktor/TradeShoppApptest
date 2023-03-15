package com.example.testcleanarch.domain.usecases

import com.example.testcleanarch.domain.models.FlashSale
import com.example.testcleanarch.domain.repository.UserRepository


class GetAllFlashUseCase(private val userRepository: UserRepository) {
    suspend fun execute():FlashSale{
        return userRepository.getAllFlash()
    }
}