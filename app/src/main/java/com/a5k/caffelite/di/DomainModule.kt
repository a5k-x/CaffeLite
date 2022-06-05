package com.a5k.caffelite.di

import com.a5k.caffelite.domain.CaffeUseCase
import com.a5k.caffelite.domain.CaffeUseCaseImpl
import com.a5k.caffelite.domain.MenuUseCase
import com.a5k.caffelite.domain.MenuUseCaseImpl
import com.a5k.caffelite.domain.accountUseCase.*
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DomainModule {

    @Binds
    @Singleton
    abstract fun bindCaffeUseCase(impl: CaffeUseCaseImpl): CaffeUseCase

    @Binds
    @Singleton
    abstract fun bindMenuUseCase(impl: MenuUseCaseImpl): MenuUseCase

    @Binds
    @Singleton
    abstract fun bindCreateAccountUseCase(impl: CreateAccountUseCaseImpl): CreateAccountUseCase

    @Binds
    @Singleton
    abstract fun bindLogoutUseCases(impl: LogoutUseCasesImpl): LogoutUseCases

    @Binds
    @Singleton
    abstract fun bindUnLoginUseCase(impl: UnLoginUseCaseImpl): UnLoginUseCase

    @Binds
    @Singleton
    abstract fun bindUpdatePasswordUseCase(impl: UpdatePasswordUseCaseImpl): UpdatePasswordUseCase
}