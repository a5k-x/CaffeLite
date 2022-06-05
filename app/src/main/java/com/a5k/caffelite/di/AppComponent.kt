package com.a5k.caffelite.di

import android.content.Context
import com.a5k.caffelite.ui.MainActivity
import com.a5k.caffelite.ui.AuthFragment
import com.a5k.caffelite.ui.CaffeListFragment
import com.a5k.caffelite.ui.MenuFragment
import com.a5k.caffelite.ui.RegistrationFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DataModule::class,
        DomainModule::class,
        ViewModelModule::class,
        CiceroneModule::class,
        NetworkModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(mainActivity: MainActivity)

    fun inject(authFragment: AuthFragment)

    fun inject(caffeListFragment: CaffeListFragment)

    fun inject(menuFragment: MenuFragment)

    fun inject(registrationFragment: RegistrationFragment)
}