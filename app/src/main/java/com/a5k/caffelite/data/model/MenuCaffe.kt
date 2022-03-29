package com.a5k.caffelite.data.model

data class MenuCaffe(
    val id: Int,
    val name: String,
    val imageURL: String,
    val price: Double,
    var count:Int = 0
)