package com.cmc.curtaincall.core.network.qualifiers

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LoggingClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RefreshTokenClient