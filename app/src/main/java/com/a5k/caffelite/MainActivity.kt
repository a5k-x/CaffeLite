package com.a5k.caffelite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.a5k.caffelite.view.IMainNavigation
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity(), IMainNavigation {

    lateinit var navigation: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation = Navigation.findNavController(this, R.id.fragment_container)
        val tool = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(tool)

        setHomeUp(true)
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


    override fun setTitleActionBar(title: String) {
        supportActionBar?.title = title
    }

    override fun setHomeUp(flag: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(flag)
    }


}