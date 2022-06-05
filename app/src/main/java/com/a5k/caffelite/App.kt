package com.a5k.caffelite

import android.app.Application
import com.a5k.caffelite.di.DaggerAppComponent

class App : Application() {

    companion object {

        lateinit var instance: App
    }

    val appComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}