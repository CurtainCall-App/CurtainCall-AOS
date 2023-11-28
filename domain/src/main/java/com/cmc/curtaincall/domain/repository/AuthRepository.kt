package com.cmc.curtaincall.domain.repository

import com.cmc.curtaincall.domain.model.auth.LoginResultModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun requestLogin(authorization: String): Flow<LoginResultModel>

    fun requestReissue(refreshToken: String): Flow<LoginResultModel>

    fun requestLogout(accessToken: String): Flow<Boolean>

    fun checkDuplicateNickname(nickname: String): Flow<Boolean>
}
