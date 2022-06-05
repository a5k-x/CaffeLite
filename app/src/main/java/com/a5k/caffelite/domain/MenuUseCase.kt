package com.a5k.caffelite.domain

import com.a5k.caffelite.domain.entity.MenuCaffe

interface MenuUseCase {

    suspend fun getMenu(idCaffe: Int): List<MenuCaffe>
}