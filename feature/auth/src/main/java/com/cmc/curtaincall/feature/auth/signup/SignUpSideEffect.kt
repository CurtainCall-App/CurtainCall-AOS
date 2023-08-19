package com.cmc.curtaincall.feature.auth.signup

import com.cmc.curtaincall.core.base.BaseSideEffect

sealed class SignUpSideEffect : BaseSideEffect {
    data class CreateMember(
        val memberId: Int
    ) : SignUpSideEffect()
}
