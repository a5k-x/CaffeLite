package com.a5k.caffelite.viewmodel

import android.accounts.Account
import android.accounts.AccountManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.a5k.caffelite.MainActivity
import com.a5k.caffelite.data.AppState
import com.a5k.caffelite.data.datasourse.RetrofitImp
import com.a5k.caffelite.data.model.AuthToken
import com.a5k.caffelite.data.model.User
import com.a5k.caffelite.data.repository.RepositoryImp
import com.a5k.caffelite.view.IMainNavigation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class MainViewModel(
    private val accountManager: AccountManager,
    private val mainNavigation: IMainNavigation
) : ViewModel() {

    private val liveData = MutableLiveData<AppState>()
    private val scope = CoroutineScope(Dispatchers.IO)

    companion object {
        private val TYPE_ACCOUNT = "com.coffelite.acc"
    }

    fun getLiveData() = liveData

    fun getToken(user: User) {
        scope.launch {
            val data = RepositoryImp(RetrofitImp()).singIn(user)
            callbackCust(data)
        }
    }

    fun registeryUser(user: User) {
        scope.launch {
            val data = RepositoryImp(RetrofitImp()).createUser(user)
            callbackCust(data)
        }
    }

    private fun callbackCust(data: Call<AuthToken>) {
        data.enqueue(object : Callback<AuthToken> {
            override fun onResponse(call: Call<AuthToken>, response: Response<AuthToken>) {
                when (response.code()) {
                    200 -> liveData.postValue(response.body()?.let { AppState.Success(it) })
                    401 -> liveData.postValue(AppState.Error(Throwable(response.message())))
                    404 -> liveData.postValue(AppState.Error(Throwable(response.message())))
                    else -> liveData.postValue(AppState.Error(Throwable(response.message())))
                }
            }

            override fun onFailure(call: Call<AuthToken>, t: Throwable) {
                liveData.postValue(AppState.Error(t))
            }

        })
    }

    //Создать аккаунт в AccountManager
    private fun createAccount(login: String, token: String): Boolean {
        val account = Account(login, TYPE_ACCOUNT)
        val success = accountManager.addAccountExplicitly(account, "", null)
        setTokenAccount(login, token)
        return success
    }

    private fun setTokenAccount(login: String, token: String) {
        val account = Account(login, TYPE_ACCOUNT)
        accountManager.setAuthToken(account, TYPE_ACCOUNT, token)
    }

    fun saveLoginShared(login: String) {
        mainNavigation.saveLoginShared(login)
    }


    fun createUser(login: String, token: String) {
        scope.launch {
            saveLoginShared(login)
            if (createAccount(login, token)) {
                liveData.postValue(AppState.StatusAccount(true))
            }else
            {setTokenAccount(login, token)
                liveData.postValue(AppState.StatusAccount(false))
            }
        }
        mainNavigation.toPointsCaffeListFragment()
    }
}