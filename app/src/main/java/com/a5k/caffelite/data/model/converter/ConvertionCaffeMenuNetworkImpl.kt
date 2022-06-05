package com.a5k.caffelite.data.model.converter

import com.a5k.caffelite.data.model.MenuCaffeNetwork
import com.a5k.caffelite.domain.entity.MenuCaffe
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConvertionCaffeMenuNetworkImpl @Inject constructor() : ConvertionMenuModel {


    override fun convertionModel(menu: List<MenuCaffeNetwork>): List<MenuCaffe> =
        menu.map { MenuCaffe(it.id, it.name, it.imageUrl, it.price, count = 0) }
}