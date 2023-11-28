package com.cmc.curtaincall.core.network.service.auth.response

import com.cmc.curtaincall.domain.model.auth.LoginResultModel

data class LoginResponse(
    val memberId: Int?,
    val accessToken: String,
    val accessTokenExpiresAt: String
) {
    fun toModel() = LoginResultModel(
        memberId = memberId,
        accessToken = accessToken,
        accessTokenExpiresAt = accessTokenExpiresAt
    )
}
