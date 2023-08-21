package com.cmc.curtaincall.data.repository

import com.cmc.curtaincall.data.source.remote.AuthRemoteSource
import com.cmc.curtaincall.domain.model.LoginResultModel
import com.cmc.curtaincall.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteSource: AuthRemoteSource
) : AuthRepository {
    override fun requestLogin(registrationId: String, accessToken: String): Flow<LoginResultModel> =
        authRemoteSource.requestLogin(
            registrationId = registrationId,
            accessToken = accessToken
        ).map { it.toModel() }

    override fun requestReissue(refreshToken: String): Flow<LoginResultModel> =
        authRemoteSource.requestReissue(refreshToken).map { it.toModel() }

    override fun requestLogout(accessToken: String): Flow<Boolean> =
        authRemoteSource.requestLogout(accessToken)
}
