package com.cmc.curtaincall.feature.auth.signup

import com.cmc.curtaincall.core.base.BaseEvent

sealed class SignUpEvent : BaseEvent {
    data class ChangeCheckState(
        val checkState: CheckState
    ) : SignUpEvent()

    data class CheckDuplicateNickname(
        val result: Boolean
    ) : SignUpEvent()
}
