package com.cmc.curtaincall.feature.auth.login

import com.cmc.curtaincall.core.base.BaseEvent
import com.cmc.curtaincall.domain.model.LoginResultModel

sealed class LoginEvent : BaseEvent {
    data class SuccessLogin(
        val loginResultModel: LoginResultModel
    ) : LoginEvent()
}
