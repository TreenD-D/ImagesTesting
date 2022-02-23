package ru.imagestesting.domain.model.settings

data class SettingsState(
    val isImageRandomized: Boolean,
    val isSoundEnabled: Boolean,
    val imageDuration: Float,
    val waitDuration: Float
)