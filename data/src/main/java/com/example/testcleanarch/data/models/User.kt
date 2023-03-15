package com.example.testcleanarch.data.models

data class User(val firstName:String,val lastName:String,val email:String,val password:String){
    fun toUserdbEntity():UserEntity = UserEntity(
        id = 0,
        firstName = firstName,
        lastName = lastName,
        email = email,
        password = password
    )
}