package com.cmc.curtaincall.feature.auth.login

internal sealed class LoginResponse {
    data class Success(val idToken: String) : LoginResponse()
    data class Failure(val errorMsg: String?) : LoginResponse()
}
