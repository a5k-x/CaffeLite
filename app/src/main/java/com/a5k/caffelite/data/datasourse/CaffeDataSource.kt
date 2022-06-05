package com.a5k.caffelite.data.datasourse

import com.a5k.caffelite.data.model.CaffeNetwork

interface CaffeDataSource {

    suspend fun getCaffe(): List<CaffeNetwork>
}