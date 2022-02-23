package ru.imagestestingapp.di

import ru.imagestesting.data.gateway.AuthGatewayImpl
import ru.imagestesting.data.gateway.NotificationGatewayImpl
import ru.imagestesting.domain.gateway.AuthGateway
import ru.imagestesting.domain.gateway.NotificationGateway
import org.koin.dsl.module
import ru.imagestesting.data.gateway.PatientsGatewayImpl
import ru.imagestesting.data.gateway.SettingsGatewayImpl
import ru.imagestesting.domain.gateway.PatientsGateway
import ru.imagestesting.domain.gateway.SettingsGateway

internal val gatewayModule = module {
    single<AuthGateway> { AuthGatewayImpl(get()) }

    single<NotificationGateway> { NotificationGatewayImpl(get(), get()) }

    single<PatientsGateway> { PatientsGatewayImpl(get()) }

    single<SettingsGateway> { SettingsGatewayImpl(get()) }
}