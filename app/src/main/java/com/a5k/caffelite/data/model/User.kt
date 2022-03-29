package com.a5k.caffelite.data.model

data class User(
    private var login: String,
    private var password: String
) {
    fun getlogin() = login
    fun getPassword() = password

    fun setLogin(name: String) {
        login = name
    }

    fun setPassword(password: String) {
        this.password = password
    }
}