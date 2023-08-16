package com.cmc.curtaincall.domain.repository

import com.cmc.curtaincall.domain.model.LoginResultModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun requestLogin(
        registrationId: String,
        accessToken: String
    ): Flow<LoginResultModel>

    fun requestReissue(): Flow<Boolean>

    fun requestLogout(): Flow<Boolean>
}