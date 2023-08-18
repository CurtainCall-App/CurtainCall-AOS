package com.cmc.curtaincall.feature.auth.login

import com.cmc.curtaincall.core.base.BaseState

data class LoginState(
    val accessToken: String = ""
): BaseState
