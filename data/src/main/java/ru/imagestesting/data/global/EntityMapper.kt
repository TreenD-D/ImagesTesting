package ru.imagestesting.data.global

import ru.imagestesting.data.storage.entity.PatientEntity
import ru.imagestesting.domain.model.patients.PatientEntry

object EntityMapper {
    val patientEntityMapper: Mapper<PatientEntry, PatientEntity> = { model ->
        PatientEntity(
            name = model.name,
            lastName = model.lastName,
            thirdName = model.thirdName,
            birthDate = model.birthDate,
            linkedImagesIds = model.linkedImagesIds
        )
    }

    val patientEntityMapperWithId: Mapper<PatientEntry, PatientEntity> = { model ->
        PatientEntity(
            id = model.id,
            name = model.name,
            lastName = model.lastName,
            thirdName = model.thirdName,
            birthDate = model.birthDate,
            linkedImagesIds = model.linkedImagesIds
        )
    }
}