package com.a5k.caffelite.data.model.converter

import com.a5k.caffelite.data.model.CaffeNetwork
import com.a5k.caffelite.domain.entity.Caffe
import com.a5k.caffelite.domain.entity.Coordinate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConvertionCaffeModelImpl @Inject constructor(): ConvertionCaffeModel {

    override fun convertionModel(caffe: List<CaffeNetwork>): List<Caffe> =
        caffe.map { Caffe(it.id, it.name, Coordinate(it.point.latitude, it.point.longitude)) }
}