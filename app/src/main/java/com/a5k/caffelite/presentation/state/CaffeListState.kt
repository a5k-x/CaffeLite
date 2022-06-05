package com.a5k.caffelite.presentation.state

import com.a5k.caffelite.domain.entity.Caffe

interface CaffeListState {
    
    data class Success(val listCaffe: List<Caffe>): CaffeListState
    data class Error (val error: Throwable): CaffeListState
}