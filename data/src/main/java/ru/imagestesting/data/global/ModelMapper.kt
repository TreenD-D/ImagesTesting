package ru.imagestesting.data.global

import ru.imagestesting.data.storage.entity.PatientEntity
import ru.imagestesting.domain.model.patients.PatientEntry

object ModelMapper {
    val patientModelMapper: Mapper<PatientEntity, PatientEntry> = { entity ->
        PatientEntry(
            id = entity.id,
            name = entity.name,
            lastName = entity.lastName,
            thirdName = entity.thirdName,
            birthDate = entity.birthDate,
            linkedImagesIds = entity.linkedImagesIds
        )
    }
}