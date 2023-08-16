package com.cmc.curtaincall.data.source.remote

import com.cmc.curtaincall.core.network.service.auth.AuthService
import com.cmc.curtaincall.core.network.service.auth.request.LoginRequest
import com.cmc.curtaincall.core.network.service.auth.response.LoginResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRemoteSource @Inject constructor(
    private val authService: AuthService
) {
    fun requestLogin(
        registrationId: String,
        accessToken: String
    ): Flow<LoginResponse> = flow {
        emit(
            authService.requestLogin(
                registrationId = registrationId,
                loginRequest = LoginRequest(accessToken)
            )
        )
    }

    fun requestReissue(): Flow<Boolean> = flow {
        emit(authService.requestReissue().isSuccessful)
    }

    fun requestLogout(): Flow<Boolean> = flow {
        emit(authService.requestLogout().success)
    }
}
