package ru.imagestesting.domain.gateway

interface AuthGateway {
    suspend fun getAuthorizationToken(): String
}