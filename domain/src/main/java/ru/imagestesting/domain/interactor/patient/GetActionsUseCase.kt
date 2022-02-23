package ru.imagestesting.domain.interactor.patient

import ru.imagestesting.domain.gateway.PatientsGateway
import ru.imagestesting.domain.global.DispatcherProvider
import ru.imagestesting.domain.global.UseCaseWithParams

class GetActionsUseCase(
    private val patientsGateway: PatientsGateway,
    dispatcherProvider: DispatcherProvider
) : UseCaseWithParams<Long, List<String>>(dispatcherProvider.io) {
    override suspend fun execute(parameters: Long): List<String> {
        return patientsGateway.getActions(parameters)
    }
}
