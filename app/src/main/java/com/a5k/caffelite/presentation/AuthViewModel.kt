package com.a5k.caffelite.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.a5k.caffelite.domain.entity.User
import com.a5k.caffelite.domain.accountUseCase.LogoutUseCases
import com.a5k.caffelite.presentation.state.AuthState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val logoutUseCases: LogoutUseCases
) : ViewModel() {

    private val _authInLiveData = MutableLiveData<AuthState>()
    val authLiveData: LiveData<AuthState> = _authInLiveData

    fun authorization(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            if (user.login.isNotEmpty() && user.password.isNotEmpty()) {
                val resultOperation =  logoutUseCases.logout(user)
                if (resultOperation) {
                    _authInLiveData.postValue(AuthState.Success)
                } else {
                    _authInLiveData.postValue(AuthState.Error(Throwable("Пользователь не найден")))
                }
            } else {
                _authInLiveData.value = AuthState.Error(Throwable("boom boom"))
            }
        }
    }
}