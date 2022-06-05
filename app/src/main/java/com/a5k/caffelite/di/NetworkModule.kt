package com.a5k.caffelite.di

import com.a5k.caffelite.data.model.AuthToken
import com.a5k.caffelite.data.model.CaffeNetwork
import com.a5k.caffelite.data.model.Coordinates
import com.a5k.caffelite.data.model.MenuCaffeNetwork
import com.a5k.caffelite.data.network.ApiCaffe
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.mock.BehaviorDelegate
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object {

        private const val BASE_URL = "http://www.test.ru"
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideNetworkBehavior(): NetworkBehavior = NetworkBehavior.create()

    @Singleton
    @Provides
    fun provideMockRetrofit(retrofit: Retrofit, behavior: NetworkBehavior): MockRetrofit =
        MockRetrofit.Builder(retrofit).networkBehavior(behavior).build()

    @Singleton
    @Provides
    fun provideBehaviorDelegate(mockRetrofit: MockRetrofit): BehaviorDelegate<ApiCaffe> =
        mockRetrofit.create(ApiCaffe::class.java)

    @Singleton
    @Provides
    fun provideMockApiService(delegate: BehaviorDelegate<ApiCaffe>): ApiCaffe = MockApiService(delegate)

    class MockApiService(private val delegate: BehaviorDelegate<ApiCaffe>) : ApiCaffe {

        private companion object {

            val CURRENCY_REMOTE_TOKEN_RESPONSE = AuthToken("sSkjDKhnv5kjsdhbvwb87rvubsdkjfvbkjr54fygv78bkadbl")

            val CURRENCY_REMOTE_CAFFE_RESPONSE =
                listOf(
                    CaffeNetwork(1, "Вкусный", Coordinates(1.1, 2.2)),
                    CaffeNetwork(2, "Не вкусный", Coordinates(1.1, 2.2)),
                    CaffeNetwork(3, "Ниче так", Coordinates(1.1, 2.2))
                )

            val CURRENCY_REMOTE_MENU_RESPONSE = listOf<MenuCaffeNetwork>(
                MenuCaffeNetwork(1, "Cappuccino", "https://latte.ru/wa-data/public/site/img/capp.jpeg", 245.0),
                MenuCaffeNetwork(2, "Americano", "https://otvet.imgsmail.ru/download/23018016_0b410bac026ad019066f4e1ab528dbc5.jpg", 350.0),
                MenuCaffeNetwork(3, "Latte", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQWQ9CNK1ZsZZHQbWXpCugcgOPCQTbWqD_PFw&usqp=CAU", 190.0),
                MenuCaffeNetwork(4, "Burger", "https://nevafood.ru/wp-content/uploads/2017/07/burger-ayam.jpg", 250.0),
                MenuCaffeNetwork(5, "Tea", "https://cdn-st1.rtr-vesti.ru/vh/pictures/xw/317/016/0.jpg", 25.0),
            )
        }

        override suspend fun authorization(name: String, password: String): AuthToken =
            delegate.returningResponse(CURRENCY_REMOTE_TOKEN_RESPONSE).authorization(name, password)

        override suspend fun registration(name: String, password: String): AuthToken =
            delegate.returningResponse(CURRENCY_REMOTE_TOKEN_RESPONSE).registration(name, password)

        override suspend fun getCaffe(): List<CaffeNetwork> =
            delegate.returningResponse(CURRENCY_REMOTE_CAFFE_RESPONSE).getCaffe()

        override suspend fun getMenu(idCaffe: Int): List<MenuCaffeNetwork> =
            delegate.returningResponse(CURRENCY_REMOTE_MENU_RESPONSE).getMenu(idCaffe)
    }
}