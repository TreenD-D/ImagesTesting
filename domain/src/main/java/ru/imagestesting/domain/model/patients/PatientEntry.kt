package ru.imagestesting.domain.model.patients

data class PatientEntry(
    val id: Long,
    val name: String,
    val lastName: String,
    val thirdName: String,
    val birthDate: String,
    val linkedImagesIds: String,
)