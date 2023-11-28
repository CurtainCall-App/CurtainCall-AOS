package com.cmc.curtaincall.domain.repository

import com.cmc.curtaincall.domain.model.auth.LoginResultModel
import kotlinx.coroutines.flow.Flow

interface TokenRepository {
    fun getAccessToken(): Flow<String>

    fun getAccessTokenExpiresAt(): Flow<String>
    fun getIdToken(): Flow<String>

    suspend fun saveToken(loginResultModel: LoginResultModel)

    suspend fun saveIdToken(idToken: String)
}
