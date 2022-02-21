package ru.imagestesting.domain.interactor.patient

import ru.imagestesting.domain.gateway.PatientsGateway
import ru.imagestesting.domain.global.DispatcherProvider
import ru.imagestesting.domain.global.UseCaseWithParams
import ru.imagestesting.domain.model.patients.PatientEntry

class AddPatientUseCase(
    private val patientsGateway: PatientsGateway,
    dispatcherProvider: DispatcherProvider
) : UseCaseWithParams<PatientEntry, Long>(dispatcherProvider.io) {
    override suspend fun execute(parameters: PatientEntry): Long {
        return patientsGateway.addPatient(parameters)
    }
}