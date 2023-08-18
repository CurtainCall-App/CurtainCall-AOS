package com.cmc.curtaincall.data.repository

import com.cmc.curtaincall.data.source.local.TokenLocalSource
import com.cmc.curtaincall.domain.repository.TokenRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val tokenLocalSource: TokenLocalSource
) : TokenRepository {
    override fun getAccessToken(): Flow<String> =
        tokenLocalSource.getAccessToken()

    override fun getAccessTokenExpiration(): Flow<String> =
        tokenLocalSource.getAccessTokenExpiration()

    override fun getRefreshToken(): Flow<String> =
        tokenLocalSource.getRefreshToken()

    override fun getRefreshTokenExpiration(): Flow<String> =
        tokenLocalSource.getRefreshTokenExpiration()
}
