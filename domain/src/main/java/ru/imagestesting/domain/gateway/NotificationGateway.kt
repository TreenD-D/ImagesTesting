package ru.imagestesting.domain.gateway

interface NotificationGateway {
    suspend fun registerNotificationToken(newToken: String)
}