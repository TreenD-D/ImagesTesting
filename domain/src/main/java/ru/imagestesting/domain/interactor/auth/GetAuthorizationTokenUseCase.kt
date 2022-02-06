package ru.imagestesting.domain.interactor.auth

import ru.imagestesting.domain.gateway.AuthGateway
import ru.imagestesting.domain.global.DispatcherProvider
import ru.imagestesting.domain.global.UseCase

class GetAuthorizationTokenUseCase(
    private val authGateway: AuthGateway,
    dispatcherProvider: DispatcherProvider
) : UseCase<String>(dispatcherProvider.io) {
    override suspend fun execute() = authGateway.getAuthorizationToken()
}