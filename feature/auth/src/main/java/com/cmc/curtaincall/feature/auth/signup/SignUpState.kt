package com.cmc.curtaincall.feature.auth.signup

import com.cmc.curtaincall.core.base.BaseState

enum class CheckState {
    None, Validate, Duplicate
}

data class SignUpState(
    val nickname: String = "",
    val checkState: CheckState = CheckState.None
) : BaseState
