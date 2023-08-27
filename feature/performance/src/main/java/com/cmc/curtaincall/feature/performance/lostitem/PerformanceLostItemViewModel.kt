package com.cmc.curtaincall.feature.performance.lostitem

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.cmc.curtaincall.core.base.BaseViewModel
import com.cmc.curtaincall.domain.model.lostItem.LostItemSearchWordModel
import com.cmc.curtaincall.domain.repository.LostItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PerformanceLostItemViewModel @Inject constructor(
    private val lostItemRepository: LostItemRepository
) : BaseViewModel<PerformanceLostItemUiState, PerformanceLostItemEvent, Nothing>(
    initialState = PerformanceLostItemUiState()
) {
    private var _searchWords = MutableStateFlow<List<LostItemSearchWordModel>>(listOf())
    val searchWords = _searchWords.asStateFlow()

    init {
        requestLostItemSearchWords()
    }

    override fun reduceState(currentState: PerformanceLostItemUiState, event: PerformanceLostItemEvent): PerformanceLostItemUiState =
        when (event) {
            is PerformanceLostItemEvent.SelectDate -> {
                currentState.copy(lostDate = event.date)
            }

            is PerformanceLostItemEvent.SelectItemType -> {
                currentState.copy(lostItemType = event.lostItemType)
            }

            is PerformanceLostItemEvent.ChangeActiveSearch -> {
                currentState.copy(isActiveSearch = event.isActiveSearch)
            }

            is PerformanceLostItemEvent.ChangeDoneSearch -> {
                currentState.copy(isDoneSearch = event.isDoneSearch)
            }

            is PerformanceLostItemEvent.SetQueryString -> {
                currentState.copy(queryString = event.queryString)
            }

            is PerformanceLostItemEvent.SearchLostItemList -> {
                currentState.copy(lostItemSearchItems = event.lostItems)
            }

            else -> currentState
        }

    fun selectLostItemDate(date: String) {
        sendAction(
            PerformanceLostItemEvent.SelectDate(
                date = date
            )
        )
    }

    fun selectLostItemType(lostItemType: LostItemType) {
        sendAction(
            PerformanceLostItemEvent.SelectItemType(
                lostItemType = lostItemType
            )
        )
    }

    fun setQueryString(queryString: String) {
        sendAction(
            PerformanceLostItemEvent.SetQueryString(
                queryString = queryString
            )
        )
    }

    fun changeActiveSearch(isActiveSearch: Boolean) {
        sendAction(
            PerformanceLostItemEvent.ChangeActiveSearch(
                isActiveSearch = isActiveSearch
            )
        )
    }

    fun changeDoneSearch(isDoneSearch: Boolean) {
        sendAction(
            PerformanceLostItemEvent.ChangeDoneSearch(
                isDoneSearch = isDoneSearch
            )
        )
    }

    fun searchLostItemList(
        facilityId: String,
        queryString: String
    ) {
        sendAction(
            PerformanceLostItemEvent.SearchLostItemList(
                lostItems = lostItemRepository
                    .fetchLostItemList(
                        facilityId,
                        null,
                        null,
                        queryString
                    ).cachedIn(viewModelScope)
            )
        )
    }

    private fun requestLostItemSearchWords() {
        lostItemRepository.getLostItemSearchWordList()
            .onEach { _searchWords.value = it }
            .launchIn(viewModelScope)
    }

    fun insertLostItemSearchWord() {
        viewModelScope.launch {
            lostItemRepository.insertLostItemSearchWord(
                lostItemSearchWordModel = LostItemSearchWordModel(
                    uiState.value.queryString,
                    System.currentTimeMillis()
                )
            )
        }
    }

    fun deleteLostItemSearchWord(lostItemSearchWordModel: LostItemSearchWordModel) {
        viewModelScope.launch {
            lostItemRepository.deleteLostItemSearchWord(lostItemSearchWordModel)
            requestLostItemSearchWords()
        }
    }

    fun deleteLostItemSearchWordList() {
        viewModelScope.launch {
            lostItemRepository.deleteLostItemSearchWordList()
        }
    }
}
