package com.example.testcleanarch.data.api

import com.example.testcleanarch.data.api.model.FlashSale
import com.example.testcleanarch.data.api.model.ItemOnTouch
import com.example.testcleanarch.data.api.model.Latest
import com.example.testcleanarch.domain.models.Search
import retrofit2.Response
import retrofit2.http.GET

interface Service{
    @GET("v3/a9ceeb6e-416d-4352-bde6-2203416576ac")
    suspend fun getAllFlashSale():Response<FlashSale>

    @GET("v3/cc0071a1-f06e-48fa-9e90-b1c2a61eaca7")
    suspend fun getAllLatest():Response<Latest>
    @GET("v3/f7f99d04-4971-45d5-92e0-70333383c239")
    suspend fun getItemOnTouch():Response<ItemOnTouch>
    @GET("v3/4c9cd822-9479-4509-803d-63197e5a9e19")
    suspend fun getSearch():Response<com.example.testcleanarch.data.api.model.Search>
}