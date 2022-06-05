package com.a5k.caffelite.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.a5k.caffelite.domain.MenuUseCase
import com.a5k.caffelite.domain.MenuUseCaseImpl
import com.a5k.caffelite.presentation.state.MenuState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class MenuViewModel @Inject constructor(private val menuUseCase: MenuUseCase) : ViewModel() {

    private val _menuLiveData = MutableLiveData<MenuState>()
    val menuLiveData: LiveData<MenuState> = _menuLiveData

    fun menu(idCaffe: Int) {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            _menuLiveData.value = MenuState.Error(throwable)
        }
        viewModelScope.launch(coroutineExceptionHandler) {
            val menu = menuUseCase.getMenu(idCaffe)
            _menuLiveData.value = MenuState.Success(menu)
        }
    }
}