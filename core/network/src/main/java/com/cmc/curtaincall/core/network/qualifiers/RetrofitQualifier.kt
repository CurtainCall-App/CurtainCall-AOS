package com.cmc.curtaincall.core.network.qualifiers

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LoggingRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RefreshTokenRetrofit
