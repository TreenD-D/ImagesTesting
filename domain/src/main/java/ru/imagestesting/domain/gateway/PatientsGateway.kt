package ru.imagestesting.domain.gateway

import kotlinx.coroutines.flow.Flow
import ru.imagestesting.domain.model.patients.PatientEntry

interface PatientsGateway {
    fun getPatients(): Flow<List<PatientEntry>>
    suspend fun getPatient(id: Long): PatientEntry
    suspend fun addPatient(patient: PatientEntry): Long
    suspend fun deletePatient(id: Long)
    suspend fun updatePatient(patientEntry: PatientEntry)
}