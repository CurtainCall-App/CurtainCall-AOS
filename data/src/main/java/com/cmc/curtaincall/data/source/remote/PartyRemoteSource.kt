package com.cmc.curtaincall.data.source.remote

import com.cmc.curtaincall.core.network.service.party.PartyService
import com.cmc.curtaincall.core.network.service.party.request.CreatePartyRequest
import com.cmc.curtaincall.core.network.service.party.request.UpdatePartyRequest
import com.cmc.curtaincall.core.network.service.party.response.CreatePartyResponse
import com.cmc.curtaincall.core.network.service.party.response.PartyDetailResponse
import com.cmc.curtaincall.core.network.service.party.response.PartyResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PartyRemoteSource @Inject constructor(
    private val partyService: PartyService
) {
    fun requestPartyList(
        page: Int,
        size: Int,
        category: String
    ): Flow<List<PartyResponse>> = flow {
        partyService.requestPartyList(
            page = page,
            size = size,
            category = category
        ).parties
    }

    fun searchPartyList(
        page: Int,
        size: Int,
        category: String,
        keyword: String
    ): Flow<List<PartyResponse>> = flow {
        partyService.searchPartyList(
            page = page,
            size = size,
            category = category,
            keyword = keyword
        ).parties
    }

    fun requestPartyDetail(
        partyId: String
    ): Flow<PartyDetailResponse> = flow {
        partyService.requestPartyDetail(
            partyId = partyId
        )
    }

    fun createParty(
        showId: String,
        showAt: String,
        title: String,
        content: String,
        maxMemberNum: Int,
        category: String
    ): Flow<CreatePartyResponse> = flow {
        partyService.createParty(
            createPartyRequest = CreatePartyRequest(
                showId = showId,
                showAt = showAt,
                title = title,
                content = content,
                maxMemberNum = maxMemberNum,
                category = category
            )
        )
    }

    fun deleteParty(
        partyId: String
    ): Flow<Boolean> = flow {
        partyService.deleteParty(partyId).isSuccessful
    }

    fun updateParty(
        title: String,
        content: String
    ): Flow<Boolean> = flow {
        partyService.updateParty(
            updatePartyRequest = UpdatePartyRequest(
                title = title,
                content = content
            )
        ).isSuccessful
    }

    fun participateParty(
        partyId: String
    ): Flow<Boolean> = flow {
        partyService.participateParty(partyId = partyId).isSuccessful
    }
}