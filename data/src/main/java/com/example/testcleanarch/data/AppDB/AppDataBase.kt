package com.example.testcleanarch.data.AppDB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testcleanarch.data.dao.UserDao
import com.example.testcleanarch.data.models.UserEntity

@Database(version = 1, entities = [UserEntity::class])
abstract class AppDataBase:RoomDatabase() {
    abstract fun getUserDao():UserDao
}