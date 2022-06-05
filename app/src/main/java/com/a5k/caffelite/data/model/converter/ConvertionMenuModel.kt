package com.a5k.caffelite.data.model.converter

import com.a5k.caffelite.data.model.MenuCaffeNetwork
import com.a5k.caffelite.domain.entity.MenuCaffe

interface ConvertionMenuModel {

    fun convertionModel(menu: List<MenuCaffeNetwork>): List<MenuCaffe>
}