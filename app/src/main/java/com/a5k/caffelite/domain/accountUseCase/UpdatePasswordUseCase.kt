package com.a5k.caffelite.domain.accountUseCase

import com.a5k.caffelite.domain.entity.User

interface UpdatePasswordUseCase {

    fun updatePassword(user: User): Boolean
}