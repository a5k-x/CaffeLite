package com.a5k.caffelite.domain.accountUseCase

import com.a5k.caffelite.data.datasourse.account.UserManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UnLoginUseCaseImpl @Inject constructor(
    private val userManager: UserManager
) : UnLoginUseCase {

    override fun unLogin() {
        userManager.unLogin()
    }
}