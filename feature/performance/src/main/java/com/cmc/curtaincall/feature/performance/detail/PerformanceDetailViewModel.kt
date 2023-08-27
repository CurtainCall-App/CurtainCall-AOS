package com.cmc.curtaincall.feature.performance.detail

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.cmc.curtaincall.core.base.BaseViewModel
import com.cmc.curtaincall.domain.model.lostItem.LostItemModel
import com.cmc.curtaincall.domain.model.review.ShowReviewModel
import com.cmc.curtaincall.domain.repository.FavoriteRepository
import com.cmc.curtaincall.domain.repository.LostItemRepository
import com.cmc.curtaincall.domain.repository.MemberRepository
import com.cmc.curtaincall.domain.repository.ReviewRepository
import com.cmc.curtaincall.domain.repository.ShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PerformanceDetailViewModel @Inject constructor(
    private val memberRepository: MemberRepository,
    private val showRepository: ShowRepository,
    private val reviewRepository: ReviewRepository,
    private val favoriteRepository: FavoriteRepository,
    private val lostItemRepository: LostItemRepository
) : BaseViewModel<PerformanceDetailState, PerformanceDetailEvent, Nothing>(
    initialState = PerformanceDetailState()
) {
    init {
        getMemberId()
    }

    var reviewItems: Flow<PagingData<ShowReviewModel>> = reviewRepository
        .fetchShowReviewList("")
        .cachedIn(viewModelScope)

    var lostItems: Flow<PagingData<LostItemModel>> = lostItemRepository
        .fetchLostItemList("", "", "", "")
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

            is PerformanceDetailEvent.LostItemList -> {
                currentState.copy(lostItems = event.lostItems)
            }

            PerformanceDetailEvent.FavoriteShow -> {
                currentState.copy(showDetailModel = currentState.showDetailModel.copy(isFavorite = true))
            }

            PerformanceDetailEvent.DeleteFavoriteShow -> {
                currentState.copy(showDetailModel = currentState.showDetailModel.copy(isFavorite = false))
            }

            is PerformanceDetailEvent.ChangeTabType -> {
                currentState.copy(tabType = event.tabType)
            }

            else -> currentState
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun requestShowDetail(showId: String) {
        showRepository.requestShowDetail(showId)
            .onEach { sendAction(PerformanceDetailEvent.ShowDetail(it)) }
            .flatMapConcat { showRepository.requestFacilityDetail(it.facilityId) }
            .onEach { sendAction(PerformanceDetailEvent.FacilityDetail(it)) }
            .flatMapConcat {
                requestLostItemList(it.id, null, null, null)
                lostItemRepository.requestLostItemList(0, 3, it.id, null, null, null)
            }
            .onEach { sendAction(PerformanceDetailEvent.LostItemList(it)) }
            .flatMapConcat { reviewRepository.requestShowReviewList(showId, 0, 3) }
            .onEach { sendAction(PerformanceDetailEvent.ShowReviewList(it)) }
            .launchIn(viewModelScope)
    }

    fun requestShowReviewList(showId: String) {
        reviewItems = reviewRepository
            .fetchShowReviewList(showId)
            .cachedIn(viewModelScope)
    }

    fun requestLostItemList(
        facilityId: String = uiState.value.facilityDetailModel.id,
        type: String? = null,
        foundDate: String? = null,
        title: String? = null
    ) {
        lostItems = lostItemRepository.fetchLostItemList(
            facilityId = facilityId,
            type = type,
            foundDate = foundDate,
            title = title
        )
    }

    fun changeTabType(tabType: TabType) {
        sendAction(
            PerformanceDetailEvent.ChangeTabType(
                tabType = tabType
            )
        )
    }

    fun checkFavoriteShows(showId: String) {
        favoriteRepository.checkFavoriteShows(listOf(showId))
            .onEach { checkFavoriteShows ->
                sendAction(
                    if (checkFavoriteShows.first().favorite) {
                        PerformanceDetailEvent.FavoriteShow
                    } else {
                        PerformanceDetailEvent.DeleteFavoriteShow
                    }
                )
            }.launchIn(viewModelScope)
    }

    fun requestFavoriteShow(showId: String) {
        favoriteRepository.requestFavoriteShow(showId)
            .onEach { sendAction(PerformanceDetailEvent.FavoriteShow) }
            .launchIn(viewModelScope)
    }

    fun deleteFavoriteShow(showId: String) {
        favoriteRepository.deleteFavoriteShow(showId)
            .onEach { sendAction(PerformanceDetailEvent.DeleteFavoriteShow) }
            .launchIn(viewModelScope)
    }

    private fun getMemberId() {
        memberRepository.getMemberId()
            .onEach { sendAction(PerformanceDetailEvent.GetMemberId(it)) }
            .launchIn(viewModelScope)
    }
}
