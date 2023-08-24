package com.cmc.curtaincall.feature.partymember.ui.create

import com.cmc.curtaincall.core.base.BaseSideEffect

sealed class PartyMemberCreateSideEffect : BaseSideEffect {
    object SuccessUpload : PartyMemberCreateSideEffect()
}
