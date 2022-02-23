package ru.imagestestingapp.feature.activeimagering

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.imagestesting.domain.interactor.patient.GetActionsUseCase
import ru.imagestesting.domain.interactor.patient.GetObjectsUseCase
import ru.imagestesting.domain.interactor.settings.GetSettingsUseCase
import ru.imagestesting.domain.interactor.settings.SetSettingsUseCase
import ru.imagestesting.domain.model.enums.ImagesType
import ru.imagestesting.domain.model.settings.SettingsState
import ru.imagestestingapp.global.dispatcher.error.ErrorHandler
import ru.imagestestingapp.global.dispatcher.event.EventDispatcher
import ru.imagestestingapp.global.dispatcher.notifier.Notifier
import ru.imagestestingapp.global.utils.asLiveData
import ru.imagestestingapp.global.viewmodel.LoaderViewModel

class ActiveImageringViewModel(
    private val getSettingsUseCase: GetSettingsUseCase,
    private val getActionsUseCase: GetActionsUseCase,
    private val getObjectsUseCase: GetObjectsUseCase,
    private val errorHandler: ErrorHandler,
    private val notifier: Notifier,
    private val eventDispatcher: EventDispatcher
) : LoaderViewModel() {


    private val _savedSettings = MutableLiveData<SettingsState>()
    val savedSettings = _savedSettings.asLiveData()

    private val _imagesList = MutableLiveData<List<String>>()
    val imagesList = _imagesList.asLiveData()

    fun initTestResources(patientId: Long, imagesType: ImagesType) {
        var randomizeImages = false
        viewModelScope.launch {
            getSettingsUseCase.invoke()
                .fold(
                    {
                        _savedSettings.value = it
                        randomizeImages = it.isImageRandomized
                    },
                    {
                        onError(it)
                    }
                )
            when (imagesType) {
                ImagesType.OBJECTS -> {
                    getObjectsUseCase.invoke(patientId)
                        .fold(
                            {
                                it.map { item ->
                                    val result = OBJECTS_ASSET + item
                                    result
                                }.also {
                                    if (randomizeImages) _imagesList.value = it.shuffled()
                                    else _imagesList.value = it
                                }
                            },
                            {
                                onError(it)
                            }
                        )
                }
                ImagesType.ACTIONS -> {
                    getActionsUseCase.invoke(patientId)
                        .fold(
                            {
                                it.map { item ->
                                    val result = ACTIONS_ASSET + item
                                    result
                                }.also {
                                    if (randomizeImages) _imagesList.value = it.shuffled()
                                    else _imagesList.value = it
                                }
                            },
                            {
                                onError(it)
                            }
                        )
                }
            }
        }
    }


    companion object {
        private const val ACTIONS_ASSET = "file:///android_asset/actions/"
        private const val OBJECTS_ASSET = "file:///android_asset/objects/"
    }
}