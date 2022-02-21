package ru.imagestestingapp.feature.patientslist

import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.map
import ru.imagestesting.domain.interactor.patient.GetPatientsUseCase
import ru.imagestestingapp.global.dispatcher.error.ErrorHandler
import ru.imagestestingapp.global.dispatcher.event.EventDispatcher
import ru.imagestestingapp.global.dispatcher.notifier.Notifier
import ru.imagestestingapp.global.utils.context
import ru.imagestestingapp.global.viewmodel.LoaderViewModel

class PatientsListViewModel(
    private val getPatientsUseCase: GetPatientsUseCase,
    private val errorHandler: ErrorHandler,
    private val notifier: Notifier,
    private val eventDispatcher: EventDispatcher
) : LoaderViewModel() {

    val patientsList = getPatientsUseCase.invoke()
        .map { it.orNull() }
        .asLiveData(context)

}