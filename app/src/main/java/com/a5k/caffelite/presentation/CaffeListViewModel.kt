package com.a5k.caffelite.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.a5k.caffelite.domain.CaffeUseCase
import com.a5k.caffelite.domain.CaffeUseCaseImpl
import com.a5k.caffelite.presentation.state.CaffeListState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject


class CaffeListViewModel @Inject constructor(
    private val caffeUseCase: CaffeUseCase
) : ViewModel() {

    private val _caffeListLiveData = MutableLiveData<CaffeListState>()
    val caffeListLiveData: LiveData<CaffeListState> = _caffeListLiveData

    fun listCaffe() {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            _caffeListLiveData.value = CaffeListState.Error(throwable)
        }
        viewModelScope.launch(coroutineExceptionHandler) {
            val listCaffe = caffeUseCase.getCaffe()
            _caffeListLiveData.value = CaffeListState.Success(listCaffe)
        }
    }
}