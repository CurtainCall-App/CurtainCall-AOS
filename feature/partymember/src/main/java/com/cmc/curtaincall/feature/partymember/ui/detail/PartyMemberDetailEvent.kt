package com.cmc.curtaincall.feature.partymember.ui.detail

import com.cmc.curtaincall.core.base.BaseEvent
import com.cmc.curtaincall.domain.model.party.PartyDetailModel

sealed class PartyMemberDetailEvent : BaseEvent {
    data class RequestPartyDetail(
        val partyDetailModel: PartyDetailModel
    ) : PartyMemberDetailEvent()
}
