package com.a5k.caffelite.presentation.state

sealed interface AuthState {

    object Success : AuthState
    data class Error(val e: Throwable) : AuthState
}