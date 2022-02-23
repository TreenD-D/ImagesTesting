package ru.imagestesting.data.global

import com.tfcporciuncula.flow.Serializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
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
            objectsIds = runCatching { Json.decodeFromString<List<String>>(entity.objectsIds) }
                .getOrDefault(emptyList()),
            actionsIds = runCatching { Json.decodeFromString<List<String>>(entity.actionsIds) }
                .getOrDefault(emptyList())
        )
    }
}