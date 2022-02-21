package ru.imagestesting.domain.interactor.patient

import ru.imagestesting.domain.gateway.PatientsGateway
import ru.imagestesting.domain.global.DispatcherProvider
import ru.imagestesting.domain.global.UseCaseWithParams
import ru.imagestesting.domain.model.patients.PatientEntry

class GetPatientByIdUseCase(
    private val patientsGateway: PatientsGateway,
    dispatcherProvider: DispatcherProvider
) : UseCaseWithParams<Long, PatientEntry>(dispatcherProvider.io) {
    override suspend fun execute(parameters: Long): PatientEntry {
        return patientsGateway.getPatient(parameters)
    }
}