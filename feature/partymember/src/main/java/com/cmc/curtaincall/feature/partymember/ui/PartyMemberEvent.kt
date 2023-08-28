package com.cmc.curtaincall.feature.partymember.ui

import androidx.paging.PagingData
import com.cmc.curtaincall.common.design.component.content.card.PartyType
import com.cmc.curtaincall.core.base.BaseEvent
import com.cmc.curtaincall.domain.model.party.PartyModel
import kotlinx.coroutines.flow.Flow

sealed class PartyMemberEvent : BaseEvent {

    data class ChangePartyType(
        val partyType: PartyType
    ) : PartyMemberEvent()

    data class ChangeActiveSearch(
        val isActiveSearch: Boolean
    ) : PartyMemberEvent()

    data class ChangeDoneSearch(
        val isDoneSearch: Boolean
    ) : PartyMemberEvent()

    data class SetQueryString(
        val queryString: String
    ) : PartyMemberEvent()

    data class SearchPartyList(
        val partySearchItems: Flow<PagingData<PartyModel>>
    ) : PartyMemberEvent()
}
