package com.a5k.caffelite.domain.entity

data class MenuCaffe(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val price: Double,
    var count:Int = 0
)