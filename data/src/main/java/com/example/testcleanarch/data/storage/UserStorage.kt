package com.example.testcleanarch.data.storage

import com.example.testcleanarch.data.models.User

interface UserStorage {
    fun save(user: User):Boolean
    fun get(): User
}