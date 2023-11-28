package com.cmc.curtaincall.core.network.service.auth

import com.cmc.curtaincall.core.network.service.auth.response.LoginResponse
import com.cmc.curtaincall.core.network.service.auth.response.LogoutResponse
import com.cmc.curtaincall.core.network.service.member.response.MemberDuplicateNickNameResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {
    @POST("login")
    suspend fun requestLogin(
        @Header("Authorization") authorization: String
    ): LoginResponse

    @POST("login/reissue")
    suspend fun requestReissue(
        @Header("Authorization") refreshToken: String
    ): LoginResponse

    @POST("logout")
    suspend fun requestLogout(
        @Header("Authorization") accessToken: String
    ): LogoutResponse

    @GET("members/duplicate/nickname")
    suspend fun checkDuplicateNickname(
        @Query("nickname") nickname: String
    ): MemberDuplicateNickNameResponse
}
