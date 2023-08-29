package com.cmc.curtaincall.feature.mypage

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.cmc.curtaincall.core.base.BaseViewModel
import com.cmc.curtaincall.domain.model.home.MyParticipationModel
import com.cmc.curtaincall.domain.model.home.MyRecruitmentModel
import com.cmc.curtaincall.domain.repository.MemberRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val memberRepository: MemberRepository
) : BaseViewModel<MyPageUiState, MyPageEvent, Nothing>(
    initialState = MyPageUiState()
) {

    private var _memberId = MutableStateFlow(0)
    val memberId: StateFlow<Int> = _memberId.asStateFlow()

    var watchingRecruitmentItems: Flow<PagingData<MyRecruitmentModel>> = memberRepository.fetchMyRecruitments(
        memberId = memberId.value,
        category = "WATCHING"
    ).cachedIn(viewModelScope)

    var foodRecruitmentItems: Flow<PagingData<MyRecruitmentModel>> = memberRepository.fetchMyRecruitments(
        memberId = memberId.value,
        category = "FOOD_CAFE"
    ).cachedIn(viewModelScope)

    var etcRecruitmentItems: Flow<PagingData<MyRecruitmentModel>> = memberRepository.fetchMyRecruitments(
        memberId = memberId.value,
        category = "ETC"
    ).cachedIn(viewModelScope)

    var watchingParticipationItems: Flow<PagingData<MyParticipationModel>> = memberRepository.fetchMyParticipations(
        memberId = memberId.value,
        category = "WATCHING"
    ).cachedIn(viewModelScope)

    var foodParticipationItems: Flow<PagingData<MyParticipationModel>> = memberRepository.fetchMyParticipations(
        memberId = memberId.value,
        category = "FOOD_CAFE"
    ).cachedIn(viewModelScope)

    var etcParticipationItems: Flow<PagingData<MyParticipationModel>> = memberRepository.fetchMyParticipations(
        memberId = memberId.value,
        category = "ETC"
    ).cachedIn(viewModelScope)
    override fun reduceState(currentState: MyPageUiState, event: MyPageEvent): MyPageUiState =
        when (event) {
            is MyPageEvent.LoadMemberInfo -> {
                currentState.copy(memberInfoModel = event.memberInfoModel)
            }
        }

    fun getMemberId() {
        memberRepository.getMemberId()
            .onEach {
                _memberId.value = it
                watchingRecruitmentItems = memberRepository.fetchMyRecruitments(
                    memberId = it,
                    category = "WATCHING"
                ).cachedIn(viewModelScope)
                foodRecruitmentItems = memberRepository.fetchMyRecruitments(
                    memberId = it,
                    category = "FOOD_CAFE"
                ).cachedIn(viewModelScope)
                etcRecruitmentItems = memberRepository.fetchMyRecruitments(
                    memberId = it,
                    category = "ETC"
                ).cachedIn(viewModelScope)
                watchingParticipationItems = memberRepository.fetchMyParticipations(
                    memberId = it,
                    category = "WATCHING"
                ).cachedIn(viewModelScope)
                foodParticipationItems = memberRepository.fetchMyParticipations(
                    memberId = it,
                    category = "WATCHING"
                ).cachedIn(viewModelScope)
                etcParticipationItems = memberRepository.fetchMyParticipations(
                    memberId = it,
                    category = "ETC"
                ).cachedIn(viewModelScope)
            }.flatMapLatest { memberRepository.requestMemberInfo(it) }
            .onEach { sendAction(MyPageEvent.LoadMemberInfo(it)) }
            .launchIn(viewModelScope)
    }
}
