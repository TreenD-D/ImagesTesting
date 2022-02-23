package ru.imagestestingapp.feature.testing

import ru.imagestesting.domain.interactor.patient.GetPatientByIdUseCase
import ru.imagestestingapp.Event
import ru.imagestestingapp.global.dispatcher.error.ErrorHandler
import ru.imagestestingapp.global.dispatcher.event.EventDispatcher
import ru.imagestestingapp.global.dispatcher.notifier.Notifier
import ru.imagestestingapp.global.viewmodel.LoaderViewModel

class TestingViewModel(
    private val getPatientByIdUseCase: GetPatientByIdUseCase,
    private val errorHandler: ErrorHandler,
    private val notifier: Notifier,
    private val eventDispatcher: EventDispatcher
) : LoaderViewModel(), EventDispatcher.EventListener {

    init {
        eventDispatcher.addEventListener(Event.ImagesListSelected::class, this)
    }

    override fun onEvent(event: Event) {
        TODO("Not yet implemented")
    }

    override fun onCleared() {
        eventDispatcher.removeEventListener(this)
        super.onCleared()
    }
}