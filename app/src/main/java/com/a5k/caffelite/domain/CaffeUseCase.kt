package com.a5k.caffelite.domain

import com.a5k.caffelite.domain.entity.Caffe

interface CaffeUseCase {

    suspend fun getCaffe(): List<Caffe>
}