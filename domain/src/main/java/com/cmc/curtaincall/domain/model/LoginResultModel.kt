package com.cmc.curtaincall.domain.model

data class LoginResultModel(
    val memberId: Int = Int.MIN_VALUE,
    val accessToken: String = "",
    val accessTokenExpiresAt: String = "",
    val refreshToken: String = "",
    val refreshTokenExpiresAt: String = ""
)
