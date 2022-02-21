package ru.imagestesting.domain.interactor.patient

import kotlinx.coroutines.flow.Flow
import ru.imagestesting.domain.gateway.PatientsGateway
import ru.imagestesting.domain.global.DispatcherProvider
import ru.imagestesting.domain.global.FlowUseCase
import ru.imagestesting.domain.model.patients.PatientEntry

class GetPatientsUseCase(
    private val patientsGateway: PatientsGateway,
    dispatcherProvider: DispatcherProvider
) : FlowUseCase<List<PatientEntry>>(dispatcherProvider.io) {
    override fun execute(): Flow<List<PatientEntry>> = patientsGateway
        .getPatients()
}