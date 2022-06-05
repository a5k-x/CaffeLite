package com.a5k.caffelite.data.datasourse.account

import com.a5k.caffelite.domain.entity.User

interface UserManager {

    suspend fun createAccount(user: User): Boolean
    suspend fun logout(user: User): Boolean
    fun unLogin()
    fun updatePassword(user: User): Boolean
}