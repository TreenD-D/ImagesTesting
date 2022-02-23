package ru.imagestestingapp.feature.patientscreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.imagestesting.domain.interactor.patient.GetPatientByIdUseCase
import ru.imagestesting.domain.model.patients.PatientEntry
import ru.imagestestingapp.global.dispatcher.error.ErrorHandler
import ru.imagestestingapp.global.dispatcher.event.EventDispatcher
import ru.imagestestingapp.global.dispatcher.notifier.Notifier
import ru.imagestestingapp.global.utils.asLiveData
import ru.imagestestingapp.global.viewmodel.LoaderViewModel

class PatientScreenViewModel(
    private val getPatientByIdUseCase: GetPatientByIdUseCase,
    private val errorHandler: ErrorHandler,
    private val notifier: Notifier,
    private val eventDispatcher: EventDispatcher
) : LoaderViewModel() {

    private val _patientEntry = MutableLiveData<PatientEntry>()
    val patientEntry = _patientEntry.asLiveData()

    fun getPatientData(patientId: Long) {
        viewModelScope.launch {
            getPatientByIdUseCase.invoke(patientId)
                .fold(
                    {
                        _patientEntry.value = it
                    },
                    {
                        onError()
                    }
                )
        }
    }
}