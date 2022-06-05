package com.a5k.caffelite.domain.accountUseCase

import com.a5k.caffelite.data.datasourse.account.UserManager
import com.a5k.caffelite.domain.entity.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogoutUseCasesImpl @Inject constructor(
    private val userManager: UserManager
) : LogoutUseCases {

    override suspend fun logout(user: User): Boolean =
        userManager.logout(user)
}