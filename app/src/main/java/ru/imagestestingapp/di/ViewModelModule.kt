package ru.imagestestingapp.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.imagestestingapp.feature.AppViewModel
import ru.imagestestingapp.feature.splash.SplashViewModel
import ru.imagestestingapp.global.viewmodel.NavigationViewModel

internal val viewModelModule = module {
    viewModel { NavigationViewModel(get()) }
    viewModel { AppViewModel() }

    viewModel { SplashViewModel(get(), get(), get()) }
}