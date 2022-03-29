package com.a5k.caffelite.data.model

data class AuthToken(
    private val token: String
) {
    fun getToken() = token
}

