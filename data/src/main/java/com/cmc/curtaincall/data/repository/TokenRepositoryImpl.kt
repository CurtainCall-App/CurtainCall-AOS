package com.cmc.curtaincall.data.repository

import com.cmc.curtaincall.data.source.local.TokenLocalSource
import com.cmc.curtaincall.domain.model.auth.LoginResultModel
import com.cmc.curtaincall.domain.repository.TokenRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val tokenLocalSource: TokenLocalSource
) : TokenRepository {
    override fun getAccessToken(): Flow<String> =
        tokenLocalSource.getAccessToken()

    override fun getAccessTokenExpiresAt(): Flow<String> =
        tokenLocalSource.getAccessTokenExpiration()

    override fun getIdToken(): Flow<String> =
        tokenLocalSource.getIdToken()

    override suspend fun saveToken(loginResultModel: LoginResultModel) {
        tokenLocalSource.saveToken(
            accessToken = loginResultModel.accessToken,
            accessTokenExpiresAt = loginResultModel.accessTokenExpiresAt
        )
    }

    override suspend fun saveIdToken(idToken: String) {
        tokenLocalSource.saveIdToken(idToken = idToken)
    }
}
