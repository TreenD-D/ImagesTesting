package ru.imagestesting.data.gateway

import ru.imagestesting.data.preference.PreferencesWrapper
import ru.imagestesting.domain.gateway.SettingsGateway
import ru.imagestesting.domain.model.settings.SettingsState

class SettingsGatewayImpl(
    private val prefs: PreferencesWrapper
) : SettingsGateway {
    override suspend fun setSettings(settings: SettingsState) {
        prefs.randomizeImages.setAndCommit(settings.isImageRandomized)
        prefs.playSound.setAndCommit(settings.isSoundEnabled)
        prefs.imageDuration.setAndCommit(settings.imageDuration)
        prefs.waitDuration.setAndCommit(settings.waitDuration)

    }

    override fun getSettings() =
        SettingsState(
            isImageRandomized = prefs.randomizeImages.get(),
            isSoundEnabled = prefs.playSound.get(),
            imageDuration = prefs.imageDuration.get(),
            waitDuration = prefs.waitDuration.get()
        )
}