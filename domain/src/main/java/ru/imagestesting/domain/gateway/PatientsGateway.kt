package ru.imagestesting.domain.gateway

import kotlinx.coroutines.flow.Flow
import ru.imagestesting.domain.model.patients.PatientEntry

interface PatientsGateway {
    fun getPatients(): Flow<List<PatientEntry>>
    suspend fun getPatient(id: Long): PatientEntry
    suspend fun addPatient(patient: PatientEntry): Long
    suspend fun deletePatient(id: Long)
    suspend fun updatePatient(patientEntry: PatientEntry)
    suspend fun getObjects(id: Long): List<String>
    suspend fun getActions(id: Long): List<String>
    suspend fun updateObjects(objects: List<String>, id: Long)
    suspend fun updateActions(actions: List<String>, id: Long)
}