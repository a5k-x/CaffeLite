package com.a5k.caffelite.data.repository


import com.a5k.caffelite.data.datasourse.CaffeDataSource
import com.a5k.caffelite.data.datasourse.MenuDataSource
import com.a5k.caffelite.data.model.CaffeNetwork
import com.a5k.caffelite.data.model.MenuCaffeNetwork
import com.a5k.caffelite.data.model.converter.ConvertionCaffeModel
import com.a5k.caffelite.data.model.converter.ConvertionMenuModel
import com.a5k.caffelite.domain.entity.Caffe
import com.a5k.caffelite.domain.entity.MenuCaffe
import com.a5k.caffelite.domain.repository.CaffeRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CaffeRepositoryImp @Inject constructor(
    private val caffeDatasource: CaffeDataSource,
    private val menuDataSource: MenuDataSource,
    private val convertionCaffeModel: ConvertionCaffeModel,
    private val convertionMenuNetworkImpl:ConvertionMenuModel
) : CaffeRepository {

    override suspend fun getCaffe(): List<Caffe> =
        convertionCaffeModel.convertionModel(caffeDatasource.getCaffe())

    override suspend fun getMenu(idCaffe: Int): List<MenuCaffe> =
        convertionMenuNetworkImpl.convertionModel(menuDataSource.getMenu(idCaffe))
}