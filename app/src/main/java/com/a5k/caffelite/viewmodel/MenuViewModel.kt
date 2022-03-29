package com.a5k.caffelite.viewmodel

import android.accounts.Account
import android.accounts.AccountManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.a5k.caffelite.data.AppState
import com.a5k.caffelite.data.datasourse.RetrofitImp
import com.a5k.caffelite.data.model.MenuCaffe
import com.a5k.caffelite.data.repository.RepositoryImp
import com.a5k.caffelite.view.IMainNavigation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuViewModel(private val accountManager: AccountManager, private val mainNavigation: IMainNavigation):ViewModel() {

    private val liveData = MutableLiveData<AppState>()
    private val scope = CoroutineScope(Dispatchers.IO)
    fun getLiveData() = liveData

    fun getToken(idPointCaffe: Int){

           val login = mainNavigation.getLastAuthLogin()
            scope.launch { val bundle =accountManager.getAuthToken(Account(login, TYPE_ACCOUNT), TYPE_ACCOUNT,null,null,null,null)
        val token = bundle.result[AccountManager.KEY_AUTHTOKEN] as String
        getMenu(idPointCaffe, token = token)}
    }

    private fun getMenu(idPointCaffe:Int, token:String){

          val data = RepositoryImp(RetrofitImp()).getListMenuCaffe(idPointCaffe,token)
          data.enqueue(object : Callback<List<MenuCaffe>> {
              override fun onResponse(
                  call: Call<List<MenuCaffe>>,
                  response: Response<List<MenuCaffe>>
              ) {
                  when (response.code()) {
                      200 -> liveData.postValue(response.body()?.let { AppState.Success(it) })
                      401 -> liveData.postValue(AppState.Error(Throwable(response.message())))
                      else -> liveData.postValue(AppState.Error(Throwable(response.message())))
                  }
              }

              override fun onFailure(call: Call<List<MenuCaffe>>, t: Throwable) {
                  liveData.postValue(AppState.Error(t))
              }

          })

    }

    companion object{
        private const val TYPE_ACCOUNT = "com.coffelite.acc"
    }

}