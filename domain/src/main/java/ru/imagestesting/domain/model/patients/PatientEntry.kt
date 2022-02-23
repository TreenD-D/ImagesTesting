package ru.imagestesting.domain.model.patients

data class PatientEntry(
    val id: Long,
    val name: String,
    val lastName: String,
    val thirdName: String,
    val birthDate: String,
    val objectsIds: List<String>,
    val actionsIds: List<String>
)