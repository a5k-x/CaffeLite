package com.a5k.caffelite.data.datasourse

import com.a5k.caffelite.data.model.AuthToken
import com.a5k.caffelite.data.model.MenuCaffe
import com.a5k.caffelite.data.model.PointCaffe
import com.a5k.caffelite.data.model.User
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitImp : IDataSource {

    private val BASE_ULR = "http://185.244.172.108:8080/"
    private val NAME_HEADER = "Authorization"
    private val VALUE_HEADER = "Bearer "

    //Зарегистрировать пользователя
    override fun createUser(user: User): Call<AuthToken> {
        return getServirewithToken().getSingUp(user)
    }

    //Авторизовать пользователя
    override fun singIn(user: User): Call<AuthToken> {
        return getServirewithToken().getSingIn(user)
    }

    //Получить список кафейн
    override fun getPointsCaffe(token: String): Call<List<PointCaffe>> {
        return getService(token).getLocationCaffe()
    }

    //Получить список меню кафе
    override fun getListMenuCaffe(id: Int, token: String): Call<List<MenuCaffe>> {
        return getService(token).getMenuCaffe(id)
    }

    private fun getService(token: String): ApiServise {
        return createRetrofit(token).create(ApiServise::class.java)
    }

    private fun getServirewithToken(): ApiServise {
        return createRetrifitwithToken().create(ApiServise::class.java)
    }

    private fun createRetrifitwithToken(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_ULR)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createRetrofit(token: String): Retrofit {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val result = chain.request().newBuilder().addHeader(
                NAME_HEADER,
                "$VALUE_HEADER$token"
            ).build()
            chain.proceed(result)
        }

        return Retrofit.Builder()
            .baseUrl(BASE_ULR)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
    }


}