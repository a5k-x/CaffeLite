package com.a5k.caffelite.data.datasourse

import com.a5k.caffelite.data.model.MenuCaffeNetwork
import com.a5k.caffelite.data.network.ApiCaffe
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MenuDataSourceImpl @Inject constructor(private val apiCaffe: ApiCaffe) : MenuDataSource {

    override suspend fun getMenu(idCaffe: Int): List<MenuCaffeNetwork> = apiCaffe.getMenu(idCaffe)
}