package com.cmc.curtaincall.domain.repository

import androidx.paging.PagingData
import com.cmc.curtaincall.domain.model.party.CreatePartyModel
import com.cmc.curtaincall.domain.model.party.PartyDetailModel
import com.cmc.curtaincall.domain.model.party.PartyModel
import kotlinx.coroutines.flow.Flow

interface PartyRepository {

    fun fetchPartyList(
        category: String
    ): Flow<PagingData<PartyModel>>

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
        partyId: Int
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
        partyId: Int
    ): Flow<Boolean>

    fun updateParty(
        title: String,
        content: String
    ): Flow<Boolean>

    fun participateParty(
        partyId: String
    ): Flow<Boolean>
}
