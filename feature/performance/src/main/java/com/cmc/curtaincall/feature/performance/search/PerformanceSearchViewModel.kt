package com.cmc.curtaincall.feature.performance.search

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.cmc.curtaincall.common.designsystem.component.custom.SortType
import com.cmc.curtaincall.core.base.BaseViewModel
import com.cmc.curtaincall.domain.model.show.ShowSearchWordModel
import com.cmc.curtaincall.domain.repository.FavoriteRepository
import com.cmc.curtaincall.domain.repository.ShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PerformanceSearchViewModel @Inject constructor(
    private val showRepository: ShowRepository,
    private val favoriteRepository: FavoriteRepository
) : BaseViewModel<PerformanceSearchUiState, PerformanceSearchEvent, Nothing>(
    initialState = PerformanceSearchUiState()
) {

    private var _searchWords = MutableStateFlow<List<ShowSearchWordModel>>(listOf())
    val searchWords = _searchWords.asStateFlow()

    init {
        requestShowSearchWords()
        loadPlayItems()
        loadMusicalItems()
    }

    override fun reduceState(currentState: PerformanceSearchUiState, event: PerformanceSearchEvent): PerformanceSearchUiState =
        when (event) {
            is PerformanceSearchEvent.ChangeLastIndex -> {
                currentState.copy(lastIndex = event.lastIndex)
            }

            is PerformanceSearchEvent.ChangeGenre -> {
                currentState.copy(genre = event.genre)
            }

            is PerformanceSearchEvent.ChangeActiveSearch -> {
                currentState.copy(isActiveSearch = event.isActiveSearch)
            }

            is PerformanceSearchEvent.ChangeDoneSearch -> {
                currentState.copy(isDoneSearch = event.isDoneSearch)
            }

            is PerformanceSearchEvent.ChangeSort -> {
                currentState.copy(sortType = event.sortType)
            }

            is PerformanceSearchEvent.SetQueryString -> {
                currentState.copy(queryString = event.queryString)
            }

            is PerformanceSearchEvent.SearchShowList -> {
                currentState.copy(showSearchItems = event.showSearchItems)
            }

            is PerformanceSearchEvent.LoadPlayItems -> {
                currentState.copy(playItems = event.playItems)
            }

            is PerformanceSearchEvent.LoadMusicalItems -> {
                currentState.copy(musicalItems = event.musicalItems)
            }
        }

    fun changeLastIndex(lastIndex: Int) {
        sendAction(PerformanceSearchEvent.ChangeLastIndex(lastIndex))
    }

    fun changeGenre(genre: String) {
        sendAction(PerformanceSearchEvent.ChangeGenre(genre))
        if (genre == "PLAY") {
            loadPlayItems()
        } else {
            loadMusicalItems()
        }
    }

    fun changeActiveSearch(isActive: Boolean) {
        sendAction(PerformanceSearchEvent.ChangeActiveSearch(isActive))
    }

    fun changeDoneSearch(isDone: Boolean) {
        sendAction(PerformanceSearchEvent.ChangeDoneSearch(isDone))
    }

    fun changeSortType(sortType: SortType) {
        sendAction(
            PerformanceSearchEvent.ChangeSort(
                sortType = sortType
            )
        )
        loadPlayItems()
        loadMusicalItems()
    }

    fun setQueryString(query: String) {
        sendAction(PerformanceSearchEvent.SetQueryString(query))
    }

    fun searchShowList(query: String) {
        sendAction(
            PerformanceSearchEvent.SearchShowList(
                showSearchItems = showRepository
                    .fetchSearchShowList(query)
                    .cachedIn(viewModelScope)
            )
        )
    }

    fun loadPlayItems() {
        sendAction(
            PerformanceSearchEvent.LoadPlayItems(
                playItems = showRepository.fetchShowList(
                    "PLAY",
                    uiState.value.sortType.code
                )
            )
        )
    }

    fun loadMusicalItems() {
        sendAction(
            PerformanceSearchEvent.LoadMusicalItems(
                musicalItems = showRepository.fetchShowList(
                    "MUSICAL",
                    uiState.value.sortType.code
                )
            )
        )
    }

    fun requestFavoriteShow(showId: String) {
        favoriteRepository.requestFavoriteShow(showId)
            .onEach {
            }.launchIn(viewModelScope)
    }

    fun deleteFavoriteShow(showId: String) {
        favoriteRepository.deleteFavoriteShow(showId)
            .onEach {
            }.launchIn(viewModelScope)
    }

    private fun requestShowSearchWords() {
        showRepository.getShowSearchWordList()
            .onEach { _searchWords.value = it }
            .launchIn(viewModelScope)
    }

    fun insertShowSearchWord() {
        viewModelScope.launch {
            showRepository.insertShowSearchWord(
                showSearchWordModel = ShowSearchWordModel(
                    uiState.value.queryString,
                    System.currentTimeMillis()
                )
            )
            requestShowSearchWords()
        }
    }

    fun deleteShowSearchWord(searchWordModel: ShowSearchWordModel) {
        viewModelScope.launch {
            showRepository.deleteShowSearchWord(searchWordModel)
            requestShowSearchWords()
        }
    }

    fun deleteShowSearchWordList() {
        viewModelScope.launch {
            showRepository.deleteShowSearchWordList()
        }
    }
}
