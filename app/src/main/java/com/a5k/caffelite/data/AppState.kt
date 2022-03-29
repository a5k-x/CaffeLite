package com.a5k.caffelite.data

sealed class AppState {
    data class Success(val item: Any) : AppState()
    data class Error(val e: Throwable) : AppState()

    data class StatusAccount(val boolean: Boolean) : AppState()
}