package com.cmc.curtaincall.feature.show.detail

import androidx.lifecycle.viewModelScope
import com.cmc.curtaincall.core.base.BaseViewModel
import com.cmc.curtaincall.domain.enums.MenuTabType
import com.cmc.curtaincall.domain.enums.ReviewSortType
import com.cmc.curtaincall.domain.repository.FavoriteRepository
import com.cmc.curtaincall.domain.repository.LostPropertyRepository
import com.cmc.curtaincall.domain.repository.ReviewRepository
import com.cmc.curtaincall.domain.repository.ShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ShowDetailViewModel @Inject constructor(
    private val showRepository: ShowRepository,
    private val reviewRepository: ReviewRepository,
    private val favoriteRepository: FavoriteRepository,
    private val lostItemRepository: LostPropertyRepository
) : BaseViewModel<ShowDetailUiState, ShowDetailEvent, Nothing>(
    initialState = ShowDetailUiState()
) {
    override fun reduceState(currentState: ShowDetailUiState, event: ShowDetailEvent): ShowDetailUiState =
        when (event) {
            is ShowDetailEvent.ChangeMenuTabType -> {
                currentState.copy(menuTabType = event.menuTabType)
            }

            is ShowDetailEvent.SelectFavorite -> {
                currentState.copy(isFavorite = event.isFavorite)
            }

            ShowDetailEvent.CloseCoachMark -> {
                currentState.copy(isShowCoachMark = false)
            }

            is ShowDetailEvent.RequestShowDetail -> {
                currentState.copy(showDetailModel = event.showDetailModel)
            }

            is ShowDetailEvent.RequestFacilityDetail -> {
                currentState.copy(facilityDetailModel = event.facilityDetailModel)
            }

            is ShowDetailEvent.RequestLostPropertyList -> {
                currentState.copy(lostProperties = event.lostProperties)
            }

            is ShowDetailEvent.RequestShowReviewList -> {
                currentState.copy(showReviews = event.showReviews)
            }
        }

    fun changeMenuTabType(menuTabType: MenuTabType) {
        sendAction(
            ShowDetailEvent.ChangeMenuTabType(
                menuTabType = menuTabType
            )
        )
    }

    fun selectFavoriteShow(
        showId: String,
        isFavorite: Boolean
    ) {
        if (isFavorite) {
            favoriteRepository.requestFavoriteShow(showId)
                .onEach { sendAction(ShowDetailEvent.SelectFavorite(true)) }
                .launchIn(viewModelScope)
        } else {
            favoriteRepository.deleteFavoriteShow(showId)
                .onEach { sendAction(ShowDetailEvent.SelectFavorite(false)) }
                .launchIn(viewModelScope)
        }
    }

    fun checkFavoriteShow(showId: String) {
        favoriteRepository.checkFavoriteShows(listOf(showId))
            .onEach { checkFavoriteShows ->
                sendAction(
                    ShowDetailEvent.SelectFavorite(
                        isFavorite = checkFavoriteShows.first().favorite
                    )
                )
            }.launchIn(viewModelScope)
    }

    fun closeCoachMark() {
        sendAction(ShowDetailEvent.CloseCoachMark)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun requestShowDetail(showId: String) {
        showRepository.requestShowDetail(showId)
            .onEach { sendAction(ShowDetailEvent.RequestShowDetail(it)) }
            .flatMapConcat { showRepository.requestFacilityDetail(it.facilityId) }
            .onEach { sendAction(ShowDetailEvent.RequestFacilityDetail(it)) }
            .flatMapConcat {
                lostItemRepository.requestLostPropertyList(
                    page = 0,
                    size = 10,
                    facilityId = it.id,
                    foundDateStart = null,
                    foundDateEnd = null
                )
            }.onEach { sendAction(ShowDetailEvent.RequestLostPropertyList(it)) }
            .flatMapConcat {
                reviewRepository.requestShowReviewList(
                    showId = showId,
                    page = 0,
                    size = 3,
                    sortType = ReviewSortType.RECENTLY
                )
            }.onEach { sendAction(ShowDetailEvent.RequestShowReviewList(it)) }
            .launchIn(viewModelScope)
    }
}
