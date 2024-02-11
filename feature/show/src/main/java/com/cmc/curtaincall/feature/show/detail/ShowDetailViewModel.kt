package com.cmc.curtaincall.feature.show.detail

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.cmc.curtaincall.core.base.BaseViewModel
import com.cmc.curtaincall.domain.enums.MenuTabType
import com.cmc.curtaincall.domain.model.lostproperty.LostPropertyModel
import com.cmc.curtaincall.domain.repository.FavoriteRepository
import com.cmc.curtaincall.domain.repository.LostPropertyRepository
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
class ShowDetailViewModel @Inject constructor(
    private val showRepository: ShowRepository,
    private val reviewRepository: ReviewRepository,
    private val favoriteRepository: FavoriteRepository,
    private val lostItemRepository: LostPropertyRepository
) : BaseViewModel<ShowDetailUiState, ShowDetailEvent, Nothing>(
    initialState = ShowDetailUiState()
) {

    var lostItems: Flow<PagingData<LostPropertyModel>> = lostItemRepository
        .fetchLostPropertyList("", "", "", "")
        .cachedIn(viewModelScope)

    override fun reduceState(currentState: ShowDetailUiState, event: ShowDetailEvent): ShowDetailUiState =
        when (event) {
            is ShowDetailEvent.ChangeMenuTabType -> {
                currentState.copy(menuTabType = event.menuTabType)
            }

            is ShowDetailEvent.GetMemberId -> {
                currentState.copy(memberId = event.memberId)
            }

            is ShowDetailEvent.ShowDetail -> {
                currentState.copy(showDetailModel = event.showDetailModel)
            }

            is ShowDetailEvent.FacilityDetail -> {
                currentState.copy(facilityDetailModel = event.facilityDetailModel)
            }

            is ShowDetailEvent.ShowReviewList -> {
                currentState.copy(showReviews = event.showReviews)
            }

            is ShowDetailEvent.LostItemList -> {
                currentState.copy(lostItems = event.lostItems)
            }

            ShowDetailEvent.FavoriteShow -> {
                currentState.copy(isFavorite = true)
            }

            ShowDetailEvent.DeleteFavoriteShow -> {
                currentState.copy(isFavorite = false)
            }

            is ShowDetailEvent.SimilarShowList -> {
                currentState.copy(similarShows = event.similarShows)
            }
        }

    fun changeMenuTabType(menuTabType: MenuTabType) {
        sendAction(
            ShowDetailEvent.ChangeMenuTabType(
                menuTabType = menuTabType
            )
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun requestShowDetail(showId: String) {
        showRepository.requestShowDetail(showId)
            .onEach { sendAction(ShowDetailEvent.ShowDetail(it)) }
            .flatMapConcat {
                requestSimilarShowList(it.facilityId)
                showRepository.requestFacilityDetail(it.facilityId)
            }
            .onEach { sendAction(ShowDetailEvent.FacilityDetail(it)) }
            .flatMapConcat {
                requestLostItemList(it.id, null, null, null)
                lostItemRepository.requestLostPropertyList(0, 3, it.id, null, null, null)
            }
            .onEach { sendAction(ShowDetailEvent.LostItemList(it)) }
            .flatMapConcat { reviewRepository.requestShowReviewList(showId, 0, 3) }
            .onEach { sendAction(ShowDetailEvent.ShowReviewList(it)) }
            .launchIn(viewModelScope)
    }

//    fun changeTabType(tabType: ShowDetailMenuTab) {
//        sendAction(
//            ShowDetailEvent.ChangeTabType(
//                tabType = tabType
//            )
//        )
//    }

    private fun requestSimilarShowList(facilityId: String) {
        showRepository.requestSimilarShowList(
            facilityId = facilityId,
            page = 0,
            size = 10,
            genre = null
        ).onEach { similarShows ->
            sendAction(
                ShowDetailEvent.SimilarShowList(
                    similarShows.filter { it.id != uiState.value.showDetailModel.id }
                )
            )
        }.launchIn(viewModelScope)
    }

    fun requestLostItemList(
        facilityId: String = uiState.value.facilityDetailModel.id,
        type: String? = null,
        foundDate: String? = null,
        title: String? = null
    ) {
        lostItems = lostItemRepository.fetchLostPropertyList(
            facilityId = facilityId,
            type = type,
            foundDate = foundDate,
            title = title
        )
    }

    fun checkFavoriteShows(showId: String) {
        favoriteRepository.checkFavoriteShows(listOf(showId))
            .onEach { checkFavoriteShows ->
                sendAction(
                    if (checkFavoriteShows.first().favorite) {
                        ShowDetailEvent.FavoriteShow
                    } else {
                        ShowDetailEvent.DeleteFavoriteShow
                    }
                )
            }.launchIn(viewModelScope)
    }

    fun requestFavoriteShow(showId: String) {
        favoriteRepository.requestFavoriteShow(showId)
            .onEach { sendAction(ShowDetailEvent.FavoriteShow) }
            .launchIn(viewModelScope)
    }

    fun deleteFavoriteShow(showId: String) {
        favoriteRepository.deleteFavoriteShow(showId)
            .onEach { sendAction(ShowDetailEvent.DeleteFavoriteShow) }
            .launchIn(viewModelScope)
    }
}
