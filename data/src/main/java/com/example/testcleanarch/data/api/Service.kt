package com.example.testcleanarch.data.api

import com.example.testcleanarch.data.api.model.FlashSale
import com.example.testcleanarch.data.api.model.Latest
import retrofit2.Response
import retrofit2.http.GET

interface Service{
    @GET("v3/a9ceeb6e-416d-4352-bde6-2203416576ac")
    suspend fun getAllFlashSale():Response<FlashSale>

    @GET("v3/cc0071a1-f06e-48fa-9e90-b1c2a61eaca7")
    suspend fun getAllLatest():Response<Latest>
}