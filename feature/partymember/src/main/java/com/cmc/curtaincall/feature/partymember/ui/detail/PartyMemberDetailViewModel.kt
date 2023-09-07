package com.cmc.curtaincall.feature.partymember.ui.detail

import androidx.lifecycle.viewModelScope
import com.cmc.curtaincall.core.base.BaseViewModel
import com.cmc.curtaincall.domain.repository.ChattingRepository
import com.cmc.curtaincall.domain.repository.MemberRepository
import com.cmc.curtaincall.domain.repository.PartyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.models.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PartyMemberDetailViewModel @Inject constructor(
    private val partyRepository: PartyRepository,
    private val chattingRepository: ChattingRepository,
    private val memberRepository: MemberRepository
) : BaseViewModel<PartyMemberDetailState, PartyMemberDetailEvent, PartyMemberDetailSideEffect>(
    initialState = PartyMemberDetailState()
) {

    override fun reduceState(currentState: PartyMemberDetailState, event: PartyMemberDetailEvent): PartyMemberDetailState =
        when (event) {
            is PartyMemberDetailEvent.RequestPartyDetail -> {
                currentState.copy(partyDetailModel = event.partyDetailModel)
            }

            is PartyMemberDetailEvent.GetMemberInfo -> {
                currentState.copy(user = event.user)
            }

            is PartyMemberDetailEvent.GetMemberToken -> {
                currentState.copy(token = event.token)
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun connectChattingClient() {
        memberRepository.getMemberId()
            .flatMapLatest {
                memberRepository.requestMemberInfo(it)
            }
            .onEach {
                sendAction(
                    PartyMemberDetailEvent.GetMemberInfo(
                        user = User(
                            id = it.id.toString(),
                            name = it.nickname,
                            image = it.imageUrl.toString()
                        )
                    )
                )
            }.flatMapLatest {
                chattingRepository.requestChattingToken()
            }.onEach {
                sendAction(
                    PartyMemberDetailEvent.GetMemberToken(
                        token = it.value
                    )
                )
            }.launchIn(viewModelScope)
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
