package com.a5k.caffelite.viewmodel

import android.accounts.Account
import android.accounts.AccountManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.a5k.caffelite.data.AppState
import com.a5k.caffelite.data.datasourse.RetrofitImp
import com.a5k.caffelite.data.model.PointCaffe
import com.a5k.caffelite.data.repository.RepositoryImp
import com.a5k.caffelite.view.IMainNavigation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PointsCaffeViewModel(private val accountManager: AccountManager, private val mainNavigation: IMainNavigation) : ViewModel() {

    private val liveData = MutableLiveData<AppState>()
    private val scope = CoroutineScope(Dispatchers.IO)
    fun getLiveData() = liveData

    companion object{
        private val TYPE_ACCOUNT = "com.coffelite.acc"
    }

    fun getToken(){
       val login =  getLastAuthLogin()
      scope.launch {
        val bundle = accountManager.getAuthToken(Account(login, TYPE_ACCOUNT), TYPE_ACCOUNT,null,null,null,null)
         val token = bundle.result[AccountManager.KEY_AUTHTOKEN] as String?
          if (token != null) {
              getListPointsCaffe(token)
          } else {
              liveData.postValue(AppState.Error(Throwable("401")))
          }
      }
    }



    private fun getListPointsCaffe(token:String){
        val data = RepositoryImp(RetrofitImp()).getPointsCaffe(token)
        data.enqueue(object: Callback<List<PointCaffe>>{
            override fun onResponse(
                call: Call<List<PointCaffe>>,
                response: Response<List<PointCaffe>>
            ) {
                when(response.code()) {
                    200 -> liveData.postValue(response.body()?.let { AppState.Success(it) })
                    401 -> liveData.postValue(AppState.Error(Throwable(response.message())))
                    404 -> liveData.postValue(AppState.Error(Throwable(response.message())))
                    else -> liveData.postValue(AppState.Error(Throwable(response.message())))
                }
            }

            override fun onFailure(call: Call<List<PointCaffe>>, t: Throwable) {
                liveData.postValue(AppState.Error(t))
            }
        })
    }

    private fun getLastAuthLogin():String {
       return mainNavigation.getLastAuthLogin()
    }

    fun toMenuFragment(id:Int){
        mainNavigation.toListMenu(id)
    }

}