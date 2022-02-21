package ru.imagestestingapp.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.imagestestingapp.feature.AppViewModel
import ru.imagestestingapp.feature.addpatient.AddPatientViewModel
import ru.imagestestingapp.feature.patientslist.PatientsListViewModel
import ru.imagestestingapp.feature.splash.SplashViewModel
import ru.imagestestingapp.global.viewmodel.NavigationViewModel

internal val viewModelModule = module {
    viewModel { NavigationViewModel(get()) }
    viewModel { AppViewModel() }

    viewModel { SplashViewModel(get(), get(), get()) }
    viewModel { PatientsListViewModel(get(), get(), get(), get()) }
    viewModel { AddPatientViewModel(get(), get(), get(), get(), get(), get(), get()) }
}