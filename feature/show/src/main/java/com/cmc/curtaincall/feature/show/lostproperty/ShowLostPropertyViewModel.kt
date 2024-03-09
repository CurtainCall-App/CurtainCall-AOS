package com.cmc.curtaincall.feature.show.lostproperty

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.cmc.curtaincall.common.designsystem.component.appbars.SearchAppBarState
import com.cmc.curtaincall.common.designsystem.component.show.lostproperty.LostPropertyType
import com.cmc.curtaincall.core.base.BaseViewModel
import com.cmc.curtaincall.domain.model.lostproperty.LostPropertyModel
import com.cmc.curtaincall.domain.model.lostproperty.LostPropertySearchWordModel
import com.cmc.curtaincall.domain.repository.LostPropertyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowLostPropertyViewModel @Inject constructor(
    private val lostPropertyRepository: LostPropertyRepository
) : BaseViewModel<ShowLostPropertyUiState, ShowLostPropertyEvent, Nothing>(
    initialState = ShowLostPropertyUiState()
) {
    private val _searchAppBarState = MutableStateFlow(SearchAppBarState())
    val searchAppBarState = _searchAppBarState.asStateFlow()

    // ///
    private var _searchWords = MutableStateFlow<List<LostPropertySearchWordModel>>(listOf())
    val searchWords = _searchWords.asStateFlow()

    private var _lostPropertySearchItems = MutableStateFlow<PagingData<LostPropertyModel>>(PagingData.empty())
    val lostPropertySearchItems = _lostPropertySearchItems.asStateFlow()

    private var _completeEffects = MutableStateFlow<Boolean>(false)
    val completeEffects = _completeEffects.asStateFlow()

    init {
        requestLostPropertySearchWords()
    }

    override fun reduceState(currentState: ShowLostPropertyUiState, event: ShowLostPropertyEvent): ShowLostPropertyUiState =
        when (event) {
            is ShowLostPropertyEvent.SelectDate -> {
                currentState.copy(lostDate = event.date)
            }

            is ShowLostPropertyEvent.SelectPropertyType -> {
                currentState.copy(lostItemType = event.lostItemType)
            }

            is ShowLostPropertyEvent.ChangeActiveSearch -> {
                currentState.copy(isActiveSearch = event.isActiveSearch)
            }

            is ShowLostPropertyEvent.ChangeDoneSearch -> {
                currentState.copy(isDoneSearch = event.isDoneSearch)
            }

            is ShowLostPropertyEvent.SetQueryString -> {
                currentState.copy(queryString = event.queryString)
            }

            is ShowLostPropertyEvent.LoadLostDetailProperty -> {
                currentState.copy(lostDetailItem = event.lostDetailItem)
            }
        }

    fun selectLostItemDate(date: String) {
        sendAction(
            ShowLostPropertyEvent.SelectDate(
                date = date
            )
        )
    }

    fun selectLostItemType(lostItemType: LostPropertyType) {
        sendAction(
            ShowLostPropertyEvent.SelectPropertyType(
                lostItemType = lostItemType
            )
        )
    }

    fun setQueryString(queryString: String) {
        sendAction(ShowLostPropertyEvent.SetQueryString(queryString))
    }

    fun changeActiveSearch(isActiveSearch: Boolean) {
        sendAction(ShowLostPropertyEvent.ChangeActiveSearch(isActiveSearch))
    }

    fun changeDoneSearch(isDoneSearch: Boolean) {
        sendAction(ShowLostPropertyEvent.ChangeDoneSearch(isDoneSearch))
    }

    fun requestLostPropertyList(
        facilityId: String,
        type: String? = null,
        foundData: String? = null
    ) {
        lostPropertyRepository.fetchLostPropertyList(
            facilityId = facilityId,
            type = type,
            foundDate = foundData
        ).onEach { _lostPropertySearchItems.value = it }.launchIn(viewModelScope)
    }

    fun requestLostDetailProperty(lostPropertyId: Int) {
        lostPropertyRepository.requestLostPropertyDetail(lostPropertyId)
            .onEach { sendAction(ShowLostPropertyEvent.LoadLostDetailProperty(it)) }
            .launchIn(viewModelScope)
    }

    private fun requestLostPropertySearchWords() {
        lostPropertyRepository.getLostPropertySearchWordList()
            .onEach { _searchWords.value = it }
            .launchIn(viewModelScope)
    }

    fun insertLostPropertySearchWord() {
        viewModelScope.launch {
            lostPropertyRepository.insertLostPropertySearchWord(
                lostItemSearchWordModel = LostPropertySearchWordModel(
                    uiState.value.queryString,
                    System.currentTimeMillis()
                )
            )
        }
    }

    fun deleteLostPropertySearchWord(lostPropertySearchWordModel: LostPropertySearchWordModel) {
        viewModelScope.launch {
            lostPropertyRepository.deleteLostPropertySearchWord(lostPropertySearchWordModel)
            requestLostPropertySearchWords()
        }
    }

    fun deleteLostPropertySearchWordList() {
        viewModelScope.launch {
            lostPropertyRepository.deleteLostPropertySearchWordList()
        }
    }

    fun completeEdit(isClear: Boolean = false) {
        _completeEffects.value = isClear.not()
    }
}
