package ru.imagestesting.domain.interactor.patient

import ru.imagestesting.domain.gateway.PatientsGateway
import ru.imagestesting.domain.global.DispatcherProvider
import ru.imagestesting.domain.global.UseCaseWithParams
import ru.imagestesting.domain.model.patients.PatientEntry

class UpdatePatientUseCase(
    private val patientsGateway: PatientsGateway,
    dispatcherProvider: DispatcherProvider
) : UseCaseWithParams<PatientEntry, Unit>(dispatcherProvider.io) {
    override suspend fun execute(parameters: PatientEntry) {
        return patientsGateway.updatePatient(parameters)
    }
}