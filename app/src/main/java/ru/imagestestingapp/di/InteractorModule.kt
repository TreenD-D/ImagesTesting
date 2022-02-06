package ru.imagestestingapp.di

import ru.imagestesting.domain.interactor.auth.GetAuthorizationTokenUseCase
import ru.imagestesting.domain.interactor.notification.RegisterFirebaseTokenUseCase
import org.koin.dsl.module

internal val interactorModule = module {
    single { GetAuthorizationTokenUseCase(get(), get()) }

    single { RegisterFirebaseTokenUseCase(get(), get()) }
}