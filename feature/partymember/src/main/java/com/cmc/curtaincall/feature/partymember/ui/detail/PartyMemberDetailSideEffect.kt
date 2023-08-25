package com.cmc.curtaincall.feature.partymember.ui.detail

import com.cmc.curtaincall.core.base.BaseSideEffect

sealed class PartyMemberDetailSideEffect : BaseSideEffect {
    object SuccessDelete : PartyMemberDetailSideEffect()
}
