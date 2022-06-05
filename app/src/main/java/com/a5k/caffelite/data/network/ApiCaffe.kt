package com.a5k.caffelite.data.network

import com.a5k.caffelite.data.model.AuthToken
import com.a5k.caffelite.data.model.CaffeNetwork
import com.a5k.caffelite.data.model.MenuCaffeNetwork
import retrofit2.http.*

interface ApiCaffe {
    @GET("/authorization")
    suspend fun authorization(
        @Query("name") name: String,
        @Query("password") password: String
    ): AuthToken

    @GET("/registration")
    suspend fun registration(
        @Query("name") name: String,
        @Query("password") password: String
    ): AuthToken

    @GET("/caffe")
    suspend fun getCaffe(): List<CaffeNetwork>

    @GET("/menu")
    suspend fun getMenu(
        @Query("id") idCaffe: Int
    ):  List<MenuCaffeNetwork>
}