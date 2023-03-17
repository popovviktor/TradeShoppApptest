package com.example.testcleanarch.domain.repository

import com.example.testcleanarch.domain.models.*


interface UserRepository {
    fun saveUserName(userModelDomain: UserModelDomain):Boolean
    fun getUserName(): UserModelDomain
    suspend fun saveDB(userModelDomain: UserModelDomain)
    suspend fun getAllDbUsers():List<UserModelDomain>
    suspend fun getAllFlash():FlashSale
    suspend fun getAllLatest():Latest
    suspend fun getItemOnTouch():ItemOnTouch
    suspend fun getSearch():Search
}