package com.cmc.curtaincall.feature.auth.login

internal sealed class LoginResponse {
    data class Success(val token: String) : LoginResponse()
    data class Failure(val errorMsg: String?) : LoginResponse()
}
