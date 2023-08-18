package com.cmc.curtaincall.feature.auth.login

import com.cmc.curtaincall.core.base.BaseState
import com.cmc.curtaincall.domain.model.LoginResultModel

data class LoginState(
    val loginResultModel: LoginResultModel = LoginResultModel()
) : BaseState
