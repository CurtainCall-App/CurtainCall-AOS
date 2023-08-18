package com.cmc.curtaincall.feature.auth.login

import com.cmc.curtaincall.core.base.BaseSideEffect

sealed class LoginSideEffect : BaseSideEffect {
    object SuccessLogin : LoginSideEffect()
    object AutoLogin : LoginSideEffect()
}
