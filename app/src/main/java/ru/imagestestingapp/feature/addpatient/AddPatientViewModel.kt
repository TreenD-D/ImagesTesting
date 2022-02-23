package ru.imagestestingapp.feature.addpatient

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.imagestesting.domain.interactor.patient.AddPatientUseCase
import ru.imagestesting.domain.interactor.patient.DeletePatientUseCase
import ru.imagestesting.domain.interactor.patient.GetPatientByIdUseCase
import ru.imagestesting.domain.interactor.patient.UpdatePatientUseCase
import ru.imagestesting.domain.model.patients.PatientEntry
import ru.imagestestingapp.global.dispatcher.error.ErrorHandler
import ru.imagestestingapp.global.dispatcher.event.EventDispatcher
import ru.imagestestingapp.global.dispatcher.notifier.Notifier
import ru.imagestestingapp.global.utils.asLiveData
import ru.imagestestingapp.global.utils.context
import ru.imagestestingapp.global.viewmodel.LoaderViewModel

class AddPatientViewModel(
    private val addPatientUseCase: AddPatientUseCase,
    private val deletePatientUseCase: DeletePatientUseCase,
    private val getPatientByIdUseCase: GetPatientByIdUseCase,
    private val updatePatientUseCase: UpdatePatientUseCase,
    private val errorHandler: ErrorHandler,
    private val notifier: Notifier,
    private val eventDispatcher: EventDispatcher
) : LoaderViewModel() {

    private val _savedPatientEntry = MutableLiveData<PatientEntry>()
    val savedPatientEntry = _savedPatientEntry.asLiveData()

    private val _selectedPatientEntry = MutableLiveData<PatientEntry>()
    val selectedPatientEntry = _selectedPatientEntry.asLiveData()

    fun getPatientData(patientId: Long) {
        viewModelScope.launch {
            getPatientByIdUseCase.invoke(patientId)
                .fold(
                    {
                        _savedPatientEntry.value = it
                    },
                    {
                        onError()
                    }
                )
        }
    }

    suspend fun deletePatient(patientId: Long) {
        deletePatientUseCase.invoke(patientId).orNull()
    }

    fun commitUserSelectedInfo(patientEntry: PatientEntry) {
        _selectedPatientEntry.value = patientEntry
    }

    suspend fun updatePatient(patientId: Long) {
        selectedPatientEntry.value?.let {
            val patientEntry = PatientEntry(
                id = patientId,
                name = it.name,
                lastName = it.lastName,
                thirdName = it.thirdName,
                birthDate = it.birthDate,
                objectsIds = savedPatientEntry.value?.objectsIds ?: emptyList(),
                actionsIds = savedPatientEntry.value?.actionsIds ?: emptyList(),
            )
            updatePatientUseCase.invoke(patientEntry)
        }

    }

    suspend fun savePatient(actionsList: List<String>, objectList: List<String>) {
        selectedPatientEntry.value?.let {
            val patientEntry = PatientEntry(
                id = -1,
                name = it.name,
                lastName = it.lastName,
                thirdName = it.thirdName,
                birthDate = it.birthDate,
                objectsIds = savedPatientEntry.value?.objectsIds ?: objectList,
                actionsIds = savedPatientEntry.value?.actionsIds ?: actionsList,
            )
            addPatientUseCase.invoke(patientEntry)
        }

    }
}