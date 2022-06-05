package com.a5k.caffelite.domain.accountUseCase

import com.a5k.caffelite.domain.entity.User

interface CreateAccountUseCase {

    suspend fun createAccount(user: User): Boolean
}