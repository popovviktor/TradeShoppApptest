package com.example.testcleanarch.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.testcleanarch.data.models.UserEntity
import com.example.testcleanarch.data.models.UserInfoTuple

@Dao
interface UserDao{
@Insert(entity = UserEntity::class)
fun insert(user:UserEntity)

@Query("SELECT * FROM users")
fun getAll():List<UserInfoTuple>
}