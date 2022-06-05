package com.a5k.caffelite.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.a5k.caffelite.App
import com.a5k.caffelite.R
import com.a5k.caffelite.databinding.ActivityMainBinding
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.google.android.material.appbar.MaterialToolbar
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var router: Router
    @Inject lateinit var navigatorHolder: NavigatorHolder
    private var vb: ActivityMainBinding? = null
    private val navigator: Navigator = AppNavigator(this, R.id.fragment_container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).appComponent.inject(this)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)
        val tool = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(tool)
        authorizationFragment()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
       Log.i("AAA","onBackPressed")
        router.exit()
    }

     private fun authorizationFragment() {
        router.replaceScreen(FragmentScreen { AuthFragment.newInstance() })
    }

    override fun onDestroy() {
        vb = null
        super.onDestroy()
    }
}