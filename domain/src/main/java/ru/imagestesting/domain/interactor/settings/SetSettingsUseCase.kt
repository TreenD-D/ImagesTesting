package ru.imagestesting.domain.interactor.settings

import ru.imagestesting.domain.gateway.SettingsGateway
import ru.imagestesting.domain.global.DispatcherProvider
import ru.imagestesting.domain.global.UseCaseWithParams
import ru.imagestesting.domain.model.settings.SettingsState

class SetSettingsUseCase(
    private val settingsGateway: SettingsGateway,
    dispatcherProvider: DispatcherProvider
) : UseCaseWithParams<SettingsState, Unit>(dispatcherProvider.io) {
    override suspend fun execute(parameters: SettingsState) {
        return settingsGateway.setSettings(parameters)
    }
}