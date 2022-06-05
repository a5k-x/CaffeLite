package com.a5k.caffelite.di

import com.a5k.caffelite.data.datasourse.account.UserManager
import com.a5k.caffelite.data.datasourse.account.UserManagerImpl
import com.a5k.caffelite.data.datasourse.CaffeDataSource
import com.a5k.caffelite.data.datasourse.CaffeDataSourceImpl
import com.a5k.caffelite.data.datasourse.MenuDataSource
import com.a5k.caffelite.data.datasourse.MenuDataSourceImpl
import com.a5k.caffelite.data.model.CaffeNetwork
import com.a5k.caffelite.data.model.MenuCaffeNetwork
import com.a5k.caffelite.data.model.converter.ConvertionCaffeModel
import com.a5k.caffelite.data.model.converter.ConvertionCaffeModelImpl
import com.a5k.caffelite.data.model.converter.ConvertionCaffeMenuNetworkImpl
import com.a5k.caffelite.data.model.converter.ConvertionMenuModel
import com.a5k.caffelite.data.repository.CaffeRepositoryImp
import com.a5k.caffelite.domain.entity.Caffe
import com.a5k.caffelite.domain.entity.MenuCaffe
import com.a5k.caffelite.domain.repository.CaffeRepository
import dagger.Binds
import dagger.Module
import javax.inject.Named
import javax.inject.Singleton

@Module
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindUserManager(impl: UserManagerImpl): UserManager

    @Binds
    @Singleton
    abstract fun bindCaffeRepository(impl: CaffeRepositoryImp): CaffeRepository

    @Binds
    @Singleton
    abstract fun bindCaffeDataSource(impl: CaffeDataSourceImpl): CaffeDataSource

    @Binds
    @Singleton
    abstract fun bindMenuDataSource(impl: MenuDataSourceImpl): MenuDataSource

    @Binds
    @Singleton
    abstract fun bindConvertionCaffeNetwork(impl: ConvertionCaffeModelImpl): ConvertionCaffeModel

    @Binds
    @Singleton
    abstract fun bindConvertionMenuNetwork(impl: ConvertionCaffeMenuNetworkImpl): ConvertionMenuModel
}