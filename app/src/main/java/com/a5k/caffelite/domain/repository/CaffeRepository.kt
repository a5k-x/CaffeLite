package com.a5k.caffelite.domain.repository


import com.a5k.caffelite.domain.entity.Caffe
import com.a5k.caffelite.domain.entity.MenuCaffe

interface CaffeRepository {

    suspend fun getCaffe(): List<Caffe>
    suspend fun getMenu(idCaffe: Int): List<MenuCaffe>
}