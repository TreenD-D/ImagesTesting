package ru.imagestesting.data.gateway

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.imagestesting.data.global.EntityMapper
import ru.imagestesting.data.global.ModelMapper
import ru.imagestesting.data.storage.dao.PatientDao
import ru.imagestesting.domain.gateway.PatientsGateway
import ru.imagestesting.domain.model.patients.PatientEntry

class PatientsGatewayImpl(
    private val patientDao: PatientDao
) : PatientsGateway {

    override fun getPatients(): Flow<List<PatientEntry>> = patientDao
        .observeAllEntities()
        .map { it.map(ModelMapper.patientModelMapper) }

    override suspend fun getPatient(id: Long): PatientEntry =
        patientDao.getEntity(id)
            .let(ModelMapper.patientModelMapper)

    override suspend fun addPatient(patient: PatientEntry): Long =
        patientDao.add(patient.let(EntityMapper.patientEntityMapper))


    override suspend fun deletePatient(id: Long) =
        patientDao.deleteSinglePatient(id)

    override suspend fun updatePatient(patientEntry: PatientEntry) {
        patientDao.update(patientEntry.let(EntityMapper.patientEntityMapperWithId))
    }

}