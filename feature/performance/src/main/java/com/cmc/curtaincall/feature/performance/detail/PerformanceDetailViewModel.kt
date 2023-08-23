package com.cmc.curtaincall.feature.performance.detail

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.cmc.curtaincall.core.base.BaseViewModel
import com.cmc.curtaincall.domain.model.review.ShowReviewModel
import com.cmc.curtaincall.domain.repository.MemberRepository
import com.cmc.curtaincall.domain.repository.ReviewRepository
import com.cmc.curtaincall.domain.repository.ShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PerformanceDetailViewModel @Inject constructor(
    private val memberRepository: MemberRepository,
    private val showRepository: ShowRepository,
    private val reviewRepository: ReviewRepository
) : BaseViewModel<PerformanceDetailState, PerformanceDetailEvent, Nothing>(
    initialState = PerformanceDetailState()
) {
    init {
        getMemberId()
    }

    var reviewItems: Flow<PagingData<ShowReviewModel>> = reviewRepository
        .fetchShowReviewList("")
        .cachedIn(viewModelScope)

    override fun reduceState(currentState: PerformanceDetailState, event: PerformanceDetailEvent): PerformanceDetailState =
        when (event) {
            is PerformanceDetailEvent.GetMemberId -> {
                currentState.copy(memberId = event.memberId)
            }

            is PerformanceDetailEvent.ShowDetail -> {
                currentState.copy(showDetailModel = event.showDetailModel)
            }

            is PerformanceDetailEvent.FacilityDetail -> {
                currentState.copy(facilityDetailModel = event.facilityDetailModel)
            }

            is PerformanceDetailEvent.ShowReviewList -> {
                currentState.copy(showReviews = event.showReviews)
            }
        }

    fun requestShowDetail(showId: String) {
        showRepository.requestShowDetail(showId)
            .onEach { sendAction(PerformanceDetailEvent.ShowDetail(it)) }
            .flatMapLatest { showRepository.requestFacilityDetail(it.facilityId) }
            .onEach { sendAction(PerformanceDetailEvent.FacilityDetail(it)) }
            .flatMapLatest { reviewRepository.requestShowReviewList(showId, 0, 3) }
            .onEach { sendAction(PerformanceDetailEvent.ShowReviewList(it)) }
            .launchIn(viewModelScope)
    }

    fun requestShowReviewList(showId: String) {
        reviewItems = reviewRepository
            .fetchShowReviewList(showId)
            .cachedIn(viewModelScope)
    }

    private fun getMemberId() {
        memberRepository.getMemberId()
            .onEach { sendAction(PerformanceDetailEvent.GetMemberId(it)) }
            .launchIn(viewModelScope)
    }
}
