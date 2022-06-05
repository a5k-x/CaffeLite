package com.a5k.caffelite.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.a5k.caffelite.domain.mvvm.ViewModelFactory
import com.a5k.caffelite.domain.mvvm.ViewModelKey
import com.a5k.caffelite.presentation.AuthViewModel
import com.a5k.caffelite.presentation.CaffeListViewModel
import com.a5k.caffelite.presentation.MenuViewModel
import com.a5k.caffelite.presentation.RegistrationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    internal abstract fun provideAuthViewModel(viewModel: AuthViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CaffeListViewModel::class)
    internal abstract fun provideCaffeListViewModel(viewModel: CaffeListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MenuViewModel::class)
    internal abstract fun provideMenuViewModel(viewModel: MenuViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegistrationViewModel::class)
    internal abstract fun provideRegistrationViewModel(viewModel: RegistrationViewModel): ViewModel

    @Binds
    abstract fun viewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}