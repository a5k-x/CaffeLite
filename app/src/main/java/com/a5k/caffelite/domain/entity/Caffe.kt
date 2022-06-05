package com.a5k.caffelite.domain.entity


data class Caffe(
    val id: Int,
    val name: String,
    val point: Coordinate
)

data class Coordinate(
    val latitude: Double,
    val longitude: Double
)