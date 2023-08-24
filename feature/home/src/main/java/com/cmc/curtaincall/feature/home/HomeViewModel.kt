package com.cmc.curtaincall.feature.home

import androidx.lifecycle.viewModelScope
import com.cmc.curtaincall.common.utility.extensions.toDday
import com.cmc.curtaincall.core.base.BaseViewModel
import com.cmc.curtaincall.domain.repository.MemberRepository
import com.cmc.curtaincall.domain.repository.ShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val memberRepository: MemberRepository,
    private val showRepository: ShowRepository
) : BaseViewModel<HomeState, HomeEvent, Nothing>(
    initialState = HomeState()
) {
    init {
        getMemberNickname()
        requestMyRecruitments()
        requestMyParticipations()
        requestPopularShowList()
        requestOpenShowList()
    }

    override fun reduceState(currentState: HomeState, event: HomeEvent): HomeState =
        when (event) {
            is HomeEvent.GetNickname -> {
                currentState.copy(nickname = event.nickname)
            }

            is HomeEvent.RequestMyRecruitment -> {
                currentState.copy(myRecruitments = event.myRecruitments)
            }

            is HomeEvent.RequestMyParticipations -> {
                currentState.copy(myParticipations = event.myParticipations)
            }

            is HomeEvent.RequestPopularShowList -> {
                currentState.copy(showRanks = event.showRanks)
            }

            is HomeEvent.RequestOpenShowList -> {
                currentState.copy(openShowInfos = event.openShowInfos)
            }
        }

    private fun getMemberNickname() {
        memberRepository.getMemberNickname()
            .onEach { sendAction(HomeEvent.GetNickname(it)) }
            .launchIn(viewModelScope)
    }

    private fun requestMyRecruitments() {
        viewModelScope.launch {
            val memberId = memberRepository.getMemberId().first()
            memberRepository.requestMyRecruitments(memberId = memberId, page = 0, size = 2, category = "WATCHING")
                .zip(memberRepository.requestMyRecruitments(memberId = memberId, page = 0, size = 2, category = "FOOD_CAFE")) { watchings, foods -> watchings + foods }
                .zip(memberRepository.requestMyRecruitments(memberId = memberId, page = 0, size = 2, category = "ETC")) { list, etcs -> list + etcs }
                .onEach { recruitments ->
                    sendAction(
                        HomeEvent.RequestMyRecruitment(
                            recruitments
                                .sortedByDescending { it.createdAt }
                                .take(2)
                        )
                    )
                }
                .launchIn(this)
        }
    }

    private fun requestMyParticipations() {
        viewModelScope.launch {
            val memberId = memberRepository.getMemberId().first()
            memberRepository.requestMyParticipations(memberId = memberId, page = 0, size = 2, category = "WATCHING")
                .zip(memberRepository.requestMyParticipations(memberId = memberId, page = 0, size = 2, category = "FOOD_CAFE")) { watchings, foods -> watchings + foods }
                .zip(memberRepository.requestMyParticipations(memberId = memberId, page = 0, size = 2, category = "ETC")) { list, etcs -> list + etcs }
                .onEach { participations ->
                    sendAction(
                        HomeEvent.RequestMyParticipations(
                            participations
                                .sortedByDescending { it.createdAt }
                                .take(2)
                        )
                    )
                }
                .launchIn(this)
        }
    }

    private fun requestPopularShowList() {
        val today = SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().time)
        showRepository.requestPopularShowList(type = "WEEK", genre = "ALL", baseDate = today)
            .onEach {
                sendAction(HomeEvent.RequestPopularShowList(it.sortedBy { it.rank }.take(10)))
            }
            .launchIn(viewModelScope)
    }

    private fun requestOpenShowList() {
        val today = SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().time)
        showRepository.requestOpenShowList(page = 0, size = 10, startDate = today)
            .onEach {
                sendAction(HomeEvent.RequestOpenShowList(it.sortedByDescending { it.startDate.toDday() }.take(10)))
            }
            .launchIn(viewModelScope)
    }
}