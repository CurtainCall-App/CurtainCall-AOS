package com.cmc.curtaincall.feature.partymember.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.cmc.curtaincall.domain.model.party.PartyModel
import com.cmc.curtaincall.domain.repository.MemberRepository
import com.cmc.curtaincall.domain.repository.PartyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PartyMemberViewModel @Inject constructor(
    private val partyRepository: PartyRepository,
    private val memberRepository: MemberRepository
) : ViewModel() {

    private val _memberId = MutableStateFlow(0)
    val memberId: StateFlow<Int> = _memberId.asStateFlow()

    val watchingItems: Flow<PagingData<PartyModel>> = partyRepository
        .fetchPartyList("WATCHING")
        .cachedIn(viewModelScope)

    val foodCafeItems: Flow<PagingData<PartyModel>> = partyRepository
        .fetchPartyList("FOOD_CAFE")
        .cachedIn(viewModelScope)

    val etcItems: Flow<PagingData<PartyModel>> = partyRepository
        .fetchPartyList("ETC")
        .cachedIn(viewModelScope)

    init {
        getMemberId()
    }

    fun getMemberId() {
        memberRepository.getMemberId()
            .onEach { _memberId.value = it }
            .launchIn(viewModelScope)
    }
}
