package com.a5k.caffelite.data.datasourse


import com.a5k.caffelite.data.model.AuthToken
import com.a5k.caffelite.data.model.MenuCaffe
import com.a5k.caffelite.data.model.PointCaffe
import com.a5k.caffelite.data.model.User
import retrofit2.Call

interface IDataSource {
    fun createUser(user: User): Call<AuthToken>
    fun singIn(user: User): Call<AuthToken>
    fun getPointsCaffe(token: String): Call<List<PointCaffe>>
    fun getListMenuCaffe(id:Int, token:String): Call<List<MenuCaffe>>
}