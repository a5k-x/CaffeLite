package com.a5k.caffelite.domain.accountUseCase

import com.a5k.caffelite.domain.entity.User

interface LogoutUseCases {

    suspend fun logout(user: User): Boolean
}