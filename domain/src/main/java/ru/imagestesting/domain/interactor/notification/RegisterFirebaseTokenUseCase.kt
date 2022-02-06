package ru.imagestesting.domain.interactor.notification

import ru.imagestesting.domain.gateway.NotificationGateway
import ru.imagestesting.domain.global.DispatcherProvider
import ru.imagestesting.domain.global.UseCaseWithParams

class RegisterFirebaseTokenUseCase(
    private val notificationGateway: NotificationGateway,
    dispatcherProvider: DispatcherProvider
) : UseCaseWithParams<String, Unit>(dispatcherProvider.io) {
    override suspend fun execute(parameters: String) =
        notificationGateway.registerNotificationToken(parameters)
}