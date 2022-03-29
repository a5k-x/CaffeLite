package com.a5k.caffelite.data.datasourse

import com.a5k.caffelite.data.model.AuthToken
import com.a5k.caffelite.data.model.MenuCaffe
import com.a5k.caffelite.data.model.PointCaffe
import com.a5k.caffelite.data.model.User
import retrofit2.Call
import retrofit2.http.*

interface ApiServise {
    //Зарегистрировать пользователя
    @Headers("Content-Type: application/json")
    @POST("/auth/register")
    fun getSingUp(@Body user: User): Call<AuthToken>

    //Авторизоваться
    @Headers("Content-Type: application/json")
    @POST("/auth/login")
    fun getSingIn(@Body user: User): Call<AuthToken>

    //Список кофейн
    @GET("/locations")
    fun getLocationCaffe(): Call<List<PointCaffe>>

    //Вывести список меню
    @GET("/location/{id}/menu")
    fun getMenuCaffe(@Path("id") id:Int): Call<List<MenuCaffe>>

}