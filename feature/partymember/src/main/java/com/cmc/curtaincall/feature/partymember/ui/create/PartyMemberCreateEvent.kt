package com.cmc.curtaincall.feature.partymember.ui.create

import com.cmc.curtaincall.core.base.BaseEvent
import com.cmc.curtaincall.domain.model.show.ShowDetailModel

sealed class PartyMemberCreateEvent : BaseEvent {
    data class SetPartyCategory(
        val category: String
    ) : PartyMemberCreateEvent()

    data class SetShowId(
        val showId: String
    ) : PartyMemberCreateEvent()

    data class RequestShowDetail(
        val showDetailModel: ShowDetailModel
    ) : PartyMemberCreateEvent()

    data class SetPartyInfo(
        val showAt: String?,
        val maxMemberNum: Int
    ) : PartyMemberCreateEvent()

    data class SetPartyDescription(
        val title: String,
        val content: String
    ) : PartyMemberCreateEvent()
}
