package ru.imagestestingapp.feature.imagesselect

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.imagestesting.domain.interactor.patient.GetActionsUseCase
import ru.imagestesting.domain.interactor.patient.GetObjectsUseCase
import ru.imagestesting.domain.interactor.patient.UpdateActionsUseCase
import ru.imagestesting.domain.interactor.patient.UpdateObjectsUseCase
import ru.imagestesting.domain.model.enums.ImagesType
import ru.imagestesting.domain.model.param.SelectionImageParam
import ru.imagestestingapp.global.dispatcher.error.ErrorHandler
import ru.imagestestingapp.global.dispatcher.event.EventDispatcher
import ru.imagestestingapp.global.dispatcher.notifier.Notifier
import ru.imagestestingapp.global.utils.asLiveData
import ru.imagestestingapp.global.viewmodel.LoaderViewModel

class ImagesSelectionViewModel(
    private val getObjectsUseCase: GetObjectsUseCase,
    private val getActionsUseCase: GetActionsUseCase,
    private val updateObjectsUseCase: UpdateObjectsUseCase,
    private val updateActionsUseCase: UpdateActionsUseCase,
    private val errorHandler: ErrorHandler,
    private val notifier: Notifier,
    private val eventDispatcher: EventDispatcher
) : LoaderViewModel() {

    private val _imagesList = MutableLiveData<List<String>>()
    val imagesList = _imagesList.asLiveData()

    private val _selectedImagesList = MutableLiveData<List<String>>()
    val selectedImagesList = _selectedImagesList.asLiveData()


    private suspend fun prepareImagesSelection(patientId: Long, imagesType: ImagesType) {
        when(imagesType){
            ImagesType.OBJECTS -> {
                getObjectsUseCase.invoke(patientId)
                    .fold(
                        {
                            _selectedImagesList.value = it
                        },
                        {
                            onError(it)
                        }
                    )
            }
            ImagesType.ACTIONS ->{
                getActionsUseCase.invoke(patientId)
                    .fold(
                        {
                            _selectedImagesList.value = it
                        },
                        {
                            onError(it)
                        }
                    )

            }
        }
    }

    fun submitImagesList(images: List<String>, patientId: Long, imagesType: ImagesType) {
        viewModelScope.launch {
            prepareImagesSelection(patientId, imagesType)
            _imagesList.value = images
        }
    }

    suspend fun saveSelectedImages(patientId: Long, imagesType: ImagesType, images: List<String>){
        val selections = SelectionImageParam(
            images, patientId
        )
        when(imagesType){
            ImagesType.OBJECTS -> {
                updateObjectsUseCase.invoke(selections)
                    .fold(
                        {
                            hideProgress()
                        },
                        {
                            onError(it)
                        }
                    )
            }
            ImagesType.ACTIONS ->{
                updateActionsUseCase.invoke(selections)
                    .fold(
                        {
                            hideProgress()
                        },
                        {
                            onError(it)
                        }
                    )

            }
        }
    }
}