package ru.imagestestingapp.feature.settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.imagestesting.domain.interactor.patient.GetPatientByIdUseCase
import ru.imagestesting.domain.interactor.settings.GetSettingsUseCase
import ru.imagestesting.domain.interactor.settings.SetSettingsUseCase
import ru.imagestesting.domain.model.patients.PatientEntry
import ru.imagestesting.domain.model.settings.SettingsState
import ru.imagestestingapp.global.dispatcher.error.ErrorHandler
import ru.imagestestingapp.global.dispatcher.event.EventDispatcher
import ru.imagestestingapp.global.dispatcher.notifier.Notifier
import ru.imagestestingapp.global.utils.asLiveData
import ru.imagestestingapp.global.viewmodel.LoaderViewModel

class SettingsViewModel(
    private val getSettingsUseCase: GetSettingsUseCase,
    private val setSettingsUseCase: SetSettingsUseCase,
    private val errorHandler: ErrorHandler,
    private val notifier: Notifier,
    private val eventDispatcher: EventDispatcher
) : LoaderViewModel() {

    private val _savedSettings = MutableLiveData<SettingsState>()
    val savedSettings = _savedSettings.asLiveData()

    init {
        getSavedSettings()
    }

    private fun getSavedSettings() {
        viewModelScope.launch {
            getSettingsUseCase.invoke()
                .fold(
                    {
                        _savedSettings.value = it
                    },
                    {
                        onError(it)
                    }
                )
        }
    }

    suspend fun saveSettings(settings: SettingsState) {
        setSettingsUseCase.invoke(settings)
    }

}