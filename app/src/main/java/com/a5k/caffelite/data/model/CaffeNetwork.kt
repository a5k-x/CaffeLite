package com.a5k.caffelite.data.model

data class CaffeNetwork(
    val id: Int,
    val name: String,
    val point: Coordinates
)

data class Coordinates(
    val latitude: Double,
    val longitude: Double
)