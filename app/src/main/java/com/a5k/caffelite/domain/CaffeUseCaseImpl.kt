package com.a5k.caffelite.domain

import com.a5k.caffelite.domain.repository.CaffeRepository
import com.a5k.caffelite.domain.entity.Caffe
import javax.inject.Inject

class CaffeUseCaseImpl @Inject constructor(
    private val repository: CaffeRepository
) : CaffeUseCase {

    override suspend fun getCaffe(): List<Caffe> =
        repository.getCaffe()
}