package com.a5k.caffelite.domain.entity

data class User(
    val login: String,
    val password: String,
    val repeatPassword: String = ""
)