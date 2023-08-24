package com.cmc.curtaincall.feature.partymember.ui.create

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.cmc.curtaincall.common.design.component.content.card.PartyType
import com.cmc.curtaincall.common.utility.extensions.changeShowAt
import com.cmc.curtaincall.core.base.BaseViewModel
import com.cmc.curtaincall.domain.model.show.ShowInfoModel
import com.cmc.curtaincall.domain.repository.PartyRepository
import com.cmc.curtaincall.domain.repository.ShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PartyMemberCreateViewModel @Inject constructor(
    private val showRepository: ShowRepository,
    private val partyRepository: PartyRepository
) : BaseViewModel<PartyMemberCreateState, PartyMemberCreateEvent, PartyMemberCreateSideEffect>(
    initialState = PartyMemberCreateState()
) {

    val playItems: Flow<PagingData<ShowInfoModel>> = showRepository.fetchShowList("PLAY")
        .cachedIn(viewModelScope)

    val musicalItems: Flow<PagingData<ShowInfoModel>> = showRepository.fetchShowList("MUSICAL")
        .cachedIn(viewModelScope)

    override fun reduceState(currentState: PartyMemberCreateState, event: PartyMemberCreateEvent): PartyMemberCreateState =
        when (event) {
            is PartyMemberCreateEvent.SetPartyCategory -> {
                currentState.copy(category = event.category)
            }

            is PartyMemberCreateEvent.SetShowId -> {
                currentState.copy(showId = event.showId)
            }

            is PartyMemberCreateEvent.RequestShowDetail -> {
                currentState.copy(showDetailModel = event.showDetailModel)
            }

            is PartyMemberCreateEvent.SetPartyInfo -> {
                currentState.copy(
                    showAt = event.showAt,
                    maxMemberNum = event.maxMemberNum
                )
            }

            is PartyMemberCreateEvent.SetPartyDescription -> {
                currentState.copy(
                    title = event.title,
                    content = event.content
                )
            }
        }

    fun setPartyCategory(partyType: PartyType) {
        sendAction(PartyMemberCreateEvent.SetPartyCategory(partyType.category))
    }

    fun setShowId(showId: String) {
        sendAction(PartyMemberCreateEvent.SetShowId(showId))
        showRepository.requestShowDetail(showId)
            .onEach { sendAction(PartyMemberCreateEvent.RequestShowDetail(it)) }
            .launchIn(viewModelScope)
    }

    fun setPartyInfo(date: String, time: String, maxMemberNum: Int) {
        sendAction(
            PartyMemberCreateEvent.SetPartyInfo(
                showAt = changeShowAt(date, time),
                maxMemberNum = maxMemberNum
            )
        )
    }

    fun setPartyDescription(title: String, content: String) {
        sendAction(
            PartyMemberCreateEvent.SetPartyDescription(
                title = title,
                content = content
            )
        )
    }

    fun createParty() {
        partyRepository.createParty(
            showId = uiState.value.showId,
            showAt = uiState.value.showAt,
            title = uiState.value.title,
            content = uiState.value.content,
            maxMemberNum = uiState.value.maxMemberNum,
            category = uiState.value.category
        )
            .onEach { sendSideEffect(PartyMemberCreateSideEffect.SuccessUpload) }
            .launchIn(viewModelScope)
    }
}
