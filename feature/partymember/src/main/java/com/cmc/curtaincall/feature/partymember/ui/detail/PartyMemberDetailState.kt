package com.cmc.curtaincall.feature.partymember.ui.detail

import com.cmc.curtaincall.core.base.BaseState
import com.cmc.curtaincall.domain.model.party.PartyDetailModel

data class PartyMemberDetailState(
    val partyDetailModel: PartyDetailModel = PartyDetailModel()
) : BaseState
