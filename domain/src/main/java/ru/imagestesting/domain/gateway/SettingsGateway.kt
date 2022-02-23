package ru.imagestesting.domain.gateway

import ru.imagestesting.domain.model.settings.SettingsState

interface SettingsGateway {
    fun getSettings(): SettingsState
    suspend fun setSettings(settings: SettingsState)
}