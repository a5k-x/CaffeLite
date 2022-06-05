package com.a5k.caffelite.data.datasourse

import com.a5k.caffelite.data.model.MenuCaffeNetwork

interface MenuDataSource {

    suspend fun getMenu(idCaffe: Int): List<MenuCaffeNetwork>
}