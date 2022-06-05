package com.a5k.caffelite.domain.accountUseCase

import com.a5k.caffelite.data.datasourse.account.UserManager
import com.a5k.caffelite.domain.entity.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdatePasswordUseCaseImpl @Inject constructor(
    private val userManager: UserManager
) : UpdatePasswordUseCase {

    override fun updatePassword(user: User): Boolean =
        userManager.updatePassword(user)
}