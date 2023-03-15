package com.example.testcleanarch.data.storage

import android.content.Context
import com.example.testcleanarch.data.models.User
private const val NAMETABLEPREF = "getSharedPrefs"
private const val FIRSTNAME = "firstnameuser1"
private const val LASTNAME = "lastnameuser1"
private const val EMAIL = "emailuser1"
private const val PASSWORD = "emailuser1"
class SharedPrefsStorage(context: Context): UserStorage {

    private val getPrefShared = context.getSharedPreferences(NAMETABLEPREF,Context.MODE_PRIVATE)
    override fun save(user: User):Boolean {
        getPrefShared.edit().putString(FIRSTNAME,user.firstName.toString()).apply()
        getPrefShared.edit().putString(LASTNAME,user.lastName).apply()
        getPrefShared.edit().putString(EMAIL,user.email).apply()
        getPrefShared.edit().putString(PASSWORD,user.password).apply()
        return true
    }

    override fun get(): User {
        val firstname = getPrefShared.getString(FIRSTNAME, FIRSTNAME) ?: FIRSTNAME
        val lastname = getPrefShared.getString(LASTNAME, LASTNAME) ?: LASTNAME
        val email = getPrefShared.getString(EMAIL, EMAIL) ?: EMAIL
        val password = getPrefShared.getString(PASSWORD, PASSWORD) ?: PASSWORD
        return User(firstName = firstname,lastName= lastname, email = email,password =password)
    }
}