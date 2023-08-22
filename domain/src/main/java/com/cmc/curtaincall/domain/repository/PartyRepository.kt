package com.cmc.curtaincall.domain.repository

import com.cmc.curtaincall.domain.model.party.CreatePartyModel
import com.cmc.curtaincall.domain.model.party.PartyDetailModel
import com.cmc.curtaincall.domain.model.party.PartyModel
import kotlinx.coroutines.flow.Flow

interface PartyRepository {
    fun requestPartyList(
        page: Int,
        size: Int,
        category: String
    ): Flow<List<PartyModel>>

    fun searchPartyList(
        page: Int,
        size: Int,
        category: String,
        keyword: String
    ): Flow<List<PartyModel>>

    fun requestPartyDetail(
        partyId: String
    ): Flow<PartyDetailModel>

    fun createParty(
        showId: String,
        showAt: String,
        title: String,
        content: String,
        maxMemberNum: Int,
        category: String
    ): Flow<CreatePartyModel>

    fun deleteParty(
        partyId: String
    ): Flow<Boolean>

    fun updateParty(
        title: String,
        content: String
    ): Flow<Boolean>

    fun participateParty(
        partyId: String
    ): Flow<Boolean>
}
