package com.a5k.caffelite.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.a5k.caffelite.domain.entity.User
import com.a5k.caffelite.domain.accountUseCase.CreateAccountUseCase
import com.a5k.caffelite.presentation.state.AuthState
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val createAccountUseCase: CreateAccountUseCase
) : ViewModel() {

    private val _registrationViewModel = MutableLiveData<AuthState>()
    val registrationViewModel: LiveData<AuthState> = _registrationViewModel

    fun registration(user: User) {
        viewModelScope.launch {
            if (user.login.isNotEmpty() && user.password.isNotEmpty() && user.repeatPassword.isNotEmpty()) {
                val resultOperation = createAccountUseCase.createAccount(user)
                if (resultOperation) {
                    _registrationViewModel.value = AuthState.Success
                } else {
                    _registrationViewModel.value = AuthState.Error(Throwable("Ошибка регистрации пользователя"))
                }
            } else {
                _registrationViewModel.value = AuthState.Error(Throwable("boom boom"))
            }
        }
    }
}