package ru.imagestesting.domain.model.param

data class SelectionImageParam(
    val images: List<String>,
    val patientId: Long
)