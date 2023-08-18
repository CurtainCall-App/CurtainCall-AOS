package com.cmc.curtaincall.domain.repository

import kotlinx.coroutines.flow.Flow

interface TokenRepository {
    fun getAccessToken(): Flow<String>

    fun getAccessTokenExpiration(): Flow<String>

    fun getRefreshToken(): Flow<String>

    fun getRefreshTokenExpiration(): Flow<String>
}
