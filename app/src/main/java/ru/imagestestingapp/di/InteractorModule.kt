package ru.imagestestingapp.di

import ru.imagestesting.domain.interactor.auth.GetAuthorizationTokenUseCase
import ru.imagestesting.domain.interactor.notification.RegisterFirebaseTokenUseCase
import org.koin.dsl.module
import ru.imagestesting.domain.interactor.patient.AddPatientUseCase
import ru.imagestesting.domain.interactor.patient.DeletePatientUseCase
import ru.imagestesting.domain.interactor.patient.GetActionsUseCase
import ru.imagestesting.domain.interactor.patient.GetObjectsUseCase
import ru.imagestesting.domain.interactor.patient.GetPatientByIdUseCase
import ru.imagestesting.domain.interactor.patient.GetPatientsUseCase
import ru.imagestesting.domain.interactor.patient.UpdateActionsUseCase
import ru.imagestesting.domain.interactor.patient.UpdateObjectsUseCase
import ru.imagestesting.domain.interactor.patient.UpdatePatientUseCase

internal val interactorModule = module {
    single { GetAuthorizationTokenUseCase(get(), get()) }

    single { RegisterFirebaseTokenUseCase(get(), get()) }
    single { GetPatientsUseCase(get(), get()) }
    single { GetPatientByIdUseCase(get(), get()) }
    single { AddPatientUseCase(get(), get()) }
    single { DeletePatientUseCase(get(), get()) }
    single { UpdatePatientUseCase(get(), get()) }
    single { GetActionsUseCase(get(), get()) }
    single { GetObjectsUseCase(get(), get()) }
    single { UpdateActionsUseCase(get(), get()) }
    single { UpdateObjectsUseCase(get(), get()) }
}