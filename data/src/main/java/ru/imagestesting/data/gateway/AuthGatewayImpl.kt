package ru.imagestesting.data.gateway

import ru.imagestesting.data.network.CommonApi
import ru.imagestesting.domain.gateway.AuthGateway

class AuthGatewayImpl(
    private val commonApi: CommonApi
) : AuthGateway {
    override suspend fun getAuthorizationToken(): String {
        commonApi.getSampleData(0.0).lat.toString()
        TODO("STUB")
    }
}