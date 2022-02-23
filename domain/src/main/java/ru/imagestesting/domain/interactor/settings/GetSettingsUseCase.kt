package ru.imagestesting.domain.interactor.settings

import ru.imagestesting.domain.gateway.SettingsGateway
import ru.imagestesting.domain.global.DispatcherProvider
import ru.imagestesting.domain.global.UseCase
import ru.imagestesting.domain.model.settings.SettingsState

class GetSettingsUseCase(
    private val settingsGateway: SettingsGateway,
    dispatcherProvider: DispatcherProvider
) : UseCase<SettingsState>(dispatcherProvider.io) {
    override suspend fun execute(): SettingsState {
        return settingsGateway.getSettings()
    }
}