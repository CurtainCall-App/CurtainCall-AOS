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
    fun requestLogin(provider: String, token: String): Flow<LoginResponse> = flow {
        emit(authService.requestLogin(provider, LoginRequest(token)))
    }

    fun requestReissue(refreshToken: String): Flow<LoginResponse> = flow {
        emit(authService.requestReissue("Bearer $refreshToken"))
    }

    fun requestLogout(accessToken: String): Flow<Boolean> = flow {
        emit(authService.requestLogout("Bearer $accessToken").success)
    }
}
