package ru.imagestesting.data.gateway

import ru.imagestesting.data.network.AuthApi
import ru.imagestesting.data.network.model.FirebaseTokenPairModel
import ru.imagestesting.data.preference.PreferencesWrapper
import ru.imagestesting.domain.gateway.NotificationGateway

class NotificationGatewayImpl(
    private val authApi: AuthApi,
    private val preferences: PreferencesWrapper
) : NotificationGateway {
    override suspend fun registerNotificationToken(newToken: String) {
        val tokenPair = FirebaseTokenPairModel(
            oldToken = preferences.firebaseToken.get(),
            newToken = newToken
        )
        preferences.firebaseToken.set(newToken)

        TODO("STUB")
    }
}