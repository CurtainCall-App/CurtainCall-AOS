package com.cmc.curtaincall.feature.partymember.ui.detail

import com.cmc.curtaincall.core.base.BaseEvent
import com.cmc.curtaincall.domain.model.party.PartyDetailModel
import io.getstream.chat.android.client.models.User

sealed class PartyMemberDetailEvent : BaseEvent {
    data class RequestPartyDetail(
        val partyDetailModel: PartyDetailModel
    ) : PartyMemberDetailEvent()

    data class GetMemberInfo(
        val user: User
    ) : PartyMemberDetailEvent()

    data class GetMemberToken(
        val token: String
    ) : PartyMemberDetailEvent()
}
