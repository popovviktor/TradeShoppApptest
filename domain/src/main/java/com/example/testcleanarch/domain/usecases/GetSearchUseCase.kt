package com.example.testcleanarch.domain.usecases

import com.example.testcleanarch.domain.models.Search
import com.example.testcleanarch.domain.repository.UserRepository

class GetSearchUseCase(private val userRepository: UserRepository) {
    suspend fun execute():Search{
        return userRepository.getSearch()
    }
}