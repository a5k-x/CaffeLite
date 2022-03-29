package com.a5k.caffelite.data.repository


import com.a5k.caffelite.data.datasourse.IDataSource
import com.a5k.caffelite.data.model.AuthToken
import com.a5k.caffelite.data.model.MenuCaffe
import com.a5k.caffelite.data.model.PointCaffe
import com.a5k.caffelite.data.model.User
import retrofit2.Call


class RepositoryImp(private val datasource:IDataSource):IRepositiry {
    override fun createUser(user: User): Call<AuthToken> {
       return datasource.createUser(user)
    }

    override fun singIn(user: User): Call<AuthToken> {
       return  datasource.singIn(user)
    }

    override fun getPointsCaffe(token: String): Call<List<PointCaffe>> {
        return datasource.getPointsCaffe(token)
    }

    override fun getListMenuCaffe(id: Int, token: String): Call<List<MenuCaffe>> {
        return datasource.getListMenuCaffe(id,token)
    }

}