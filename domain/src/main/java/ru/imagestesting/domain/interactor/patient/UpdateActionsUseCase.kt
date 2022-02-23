package ru.imagestesting.domain.interactor.patient

import ru.imagestesting.domain.gateway.PatientsGateway
import ru.imagestesting.domain.global.DispatcherProvider
import ru.imagestesting.domain.global.UseCaseWithParams
import ru.imagestesting.domain.model.param.SelectionImageParam

class UpdateActionsUseCase(
    private val patientsGateway: PatientsGateway,
    dispatcherProvider: DispatcherProvider
) : UseCaseWithParams<SelectionImageParam, Unit>(dispatcherProvider.io) {
    override suspend fun execute(parameters: SelectionImageParam) {
        return patientsGateway.updateActions(actions = parameters.images, id = parameters.patientId)
    }
}