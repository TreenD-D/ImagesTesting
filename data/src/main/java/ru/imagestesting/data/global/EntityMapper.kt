package ru.imagestesting.data.global

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.imagestesting.data.storage.entity.PatientEntity
import ru.imagestesting.domain.model.patients.PatientEntry

object EntityMapper {
    val patientEntityMapper: Mapper<PatientEntry, PatientEntity> = { model ->
        PatientEntity(
            name = model.name,
            lastName = model.lastName,
            thirdName = model.thirdName,
            birthDate = model.birthDate,
            objectsIds = Json.encodeToString(model.objectsIds),
            actionsIds = Json.encodeToString(model.actionsIds)
        )
    }

    val patientEntityMapperWithId: Mapper<PatientEntry, PatientEntity> = { model ->
        PatientEntity(
            id = model.id,
            name = model.name,
            lastName = model.lastName,
            thirdName = model.thirdName,
            birthDate = model.birthDate,
            objectsIds = Json.encodeToString(model.objectsIds),
            actionsIds = Json.encodeToString(model.actionsIds)
        )
    }
}