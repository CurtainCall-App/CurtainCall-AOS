package com.cmc.curtaincall.core.network.service.auth

import com.cmc.curtaincall.core.network.service.auth.request.LoginRequest
import com.cmc.curtaincall.core.network.service.auth.response.LoginResponse
import com.cmc.curtaincall.core.network.service.auth.response.LogoutResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthService {
    @POST("login/oauth2/token/{registrationId}")
    suspend fun requestLogin(
        @Path("registrationId") registrationId: String,
        @Body loginRequest: LoginRequest
    ): LoginResponse

    @POST("login/reissue")
    suspend fun requestReissue(
        @Header("Authorization") refreshToken: String
    ): LoginResponse

    @POST("logout")
    suspend fun requestLogout(
        @Header("Authorization") accessToken: String
    ): LogoutResponse
}
