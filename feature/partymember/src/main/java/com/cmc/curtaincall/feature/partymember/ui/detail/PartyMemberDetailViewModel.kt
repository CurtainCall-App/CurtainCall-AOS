package com.cmc.curtaincall.feature.partymember.ui.detail

import androidx.lifecycle.viewModelScope
import com.cmc.curtaincall.core.base.BaseViewModel
import com.cmc.curtaincall.domain.repository.PartyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PartyMemberDetailViewModel @Inject constructor(
    private val partyRepository: PartyRepository
) : BaseViewModel<PartyMemberDetailState, PartyMemberDetailEvent, PartyMemberDetailSideEffect>(
    initialState = PartyMemberDetailState()
) {
    override fun reduceState(currentState: PartyMemberDetailState, event: PartyMemberDetailEvent): PartyMemberDetailState =
        when (event) {
            is PartyMemberDetailEvent.RequestPartyDetail -> {
                currentState.copy(partyDetailModel = event.partyDetailModel)
            }

            else -> currentState
        }

    fun requestPartyDetail(partyId: Int) {
        partyRepository.requestPartyDetail(partyId)
            .onEach { sendAction(PartyMemberDetailEvent.RequestPartyDetail(it)) }
            .launchIn(viewModelScope)
    }

    fun deleteParty(partyId: Int) {
        partyRepository.deleteParty(partyId)
            .onEach { sendSideEffect(PartyMemberDetailSideEffect.SuccessDelete) }
            .launchIn(viewModelScope)
    }

    fun participateParty(partyId: Int) {
        partyRepository.participateParty(partyId)
            .onEach { sendSideEffect(PartyMemberDetailSideEffect.SuccessParticipation) }
            .launchIn(viewModelScope)
    }
}
