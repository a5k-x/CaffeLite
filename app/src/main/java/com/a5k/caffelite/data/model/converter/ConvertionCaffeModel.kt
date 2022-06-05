package com.a5k.caffelite.data.model.converter

import com.a5k.caffelite.data.model.CaffeNetwork
import com.a5k.caffelite.domain.entity.Caffe

interface ConvertionCaffeModel {

    fun convertionModel(caffe: List<CaffeNetwork>): List<Caffe>
}