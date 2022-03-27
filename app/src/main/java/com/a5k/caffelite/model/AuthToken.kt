package com.a5k.caffelite.model

data class AuthToken(
    private val token: String
) {
    fun getToken() = token
}

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

data class PointCaffe(
    val id: Int,
    val name: String,
    val point: Coordinates
)

data class Coordinates(
    val latitude: Double,
    val longitude: Double
)

data class MenuCaffe(
    val id: Int,
    val name: String,
    val imageURL: String,
    val price: Double
)