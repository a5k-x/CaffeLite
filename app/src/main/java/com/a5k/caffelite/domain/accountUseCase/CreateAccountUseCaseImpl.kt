package com.a5k.caffelite.domain.accountUseCase

import com.a5k.caffelite.data.datasourse.account.UserManager
import com.a5k.caffelite.domain.entity.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreateAccountUseCaseImpl @Inject constructor(
    private val userManager: UserManager
) : CreateAccountUseCase {

    override suspend fun createAccount(user: User): Boolean =
        userManager.createAccount(user)
}