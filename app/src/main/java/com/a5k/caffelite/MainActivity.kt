package com.a5k.caffelite

import android.accounts.AccountManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.a5k.caffelite.view.IMainNavigation
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity(), IMainNavigation {

    lateinit var navigation: NavController
    lateinit var accountManager:AccountManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation = Navigation.findNavController(this, R.id.fragment_container)
        val tool = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(tool)
        setHomeUp(true)
        accountManager = AccountManager.get(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        navigation.popBackStack()
        return true
    }

    override fun toSingInFragment() {
        navigation.navigate(R.id.singInFragment)
    }

    override fun toSingOutFragment() {
        navigation.navigate(R.id.singOutFragment)
    }

    override fun toPointsCaffeListFragment() {
        navigation.navigate(R.id.pointsCaffeListFragment)
    }

    override fun toListMenu(idPointCaffe: Int) {
        val bundle = Bundle().apply {
            putInt(ID_POINT_CAFFE, idPointCaffe)
        }
        navigation.navigate(R.id.action_pointsCaffeListFragment_to_menuFragment,bundle)
    }

    override fun setTitleActionBar(title: String) {
        supportActionBar?.title = title
    }

    override fun setHomeUp(flag: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(flag)
    }

    //Сохранить пользователя
    override fun saveLoginShared(login: String) {
        val sh = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)
        val editor = sh.edit()
        editor.putString(KEY_NAME, login).apply()

    }

    //Пернуть логин последнего авторизованного пользователя
    override fun getLastAuthLogin(): String {
        val sh = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)
        return sh.getString(KEY_NAME, null).toString()
    }

    companion object {
        private const val APP_PREFERENCES = "APP_PREF"
        private const val KEY_NAME = "AccountName"
        private const val ID_POINT_CAFFE = "idPointCaffe"
    }

}