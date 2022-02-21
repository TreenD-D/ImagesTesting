package ru.imagestesting.domain.interactor.patient

import ru.imagestesting.domain.gateway.PatientsGateway
import ru.imagestesting.domain.global.DispatcherProvider
import ru.imagestesting.domain.global.UseCaseWithParams

class DeletePatientUseCase(
    private val patientsGateway: PatientsGateway,
    dispatcherProvider: DispatcherProvider
) : UseCaseWithParams<Long, Unit>(dispatcherProvider.io) {
    override suspend fun execute(parameters: Long) {
        return patientsGateway.deletePatient(parameters)
    }
}