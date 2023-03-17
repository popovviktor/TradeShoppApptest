package com.example.testcleanarch.data.api

class RemoteDataSource(private val service: Service) {
    suspend fun getAllFlashSale() = service.getAllFlashSale()

    suspend fun getAllLatest() = service.getAllLatest()

    suspend fun getItemOnTouch() = service.getItemOnTouch()

    suspend fun getSearch() = service.getSearch()
}