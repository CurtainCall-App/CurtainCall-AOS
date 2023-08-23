package com.cmc.curtaincall.feature.partymember.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.cmc.curtaincall.domain.model.party.PartyModel
import com.cmc.curtaincall.domain.repository.PartyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PartyMemberViewModel @Inject constructor(
    private val partyRepository: PartyRepository
) : ViewModel() {

    val watchingItems: Flow<PagingData<PartyModel>> = partyRepository
        .fetchPartyList("WATCHING")
        .cachedIn(viewModelScope)

    val foodCafeItems: Flow<PagingData<PartyModel>> = partyRepository
        .fetchPartyList("FOOD_CAFE")
        .cachedIn(viewModelScope)

    val etcItems: Flow<PagingData<PartyModel>> = partyRepository
        .fetchPartyList("ETC")
        .cachedIn(viewModelScope)
}
