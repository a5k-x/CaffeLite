package com.a5k.caffelite.data.datasourse

import com.a5k.caffelite.data.model.CaffeNetwork
import com.a5k.caffelite.data.network.ApiCaffe
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CaffeDataSourceImpl @Inject constructor(private val apiCaffe: ApiCaffe) : CaffeDataSource {

    override suspend fun getCaffe(): List<CaffeNetwork> = apiCaffe.getCaffe()
}