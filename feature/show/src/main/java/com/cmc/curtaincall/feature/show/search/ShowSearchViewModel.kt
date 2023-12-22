package com.cmc.curtaincall.feature.show.search

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.cmc.curtaincall.core.base.BaseViewModel
import com.cmc.curtaincall.domain.type.ShowGenreType
import com.cmc.curtaincall.domain.type.ShowSortType
import com.cmc.curtaincall.domain.model.show.ShowInfoModel
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
class ShowSearchViewModel @Inject constructor(
    private val showRepository: ShowRepository,
    private val favoriteRepository: FavoriteRepository
) : BaseViewModel<ShowSearchUiState, ShowSearchEvent, Nothing>(
    initialState = ShowSearchUiState()
) {

    private var _searchWords = MutableStateFlow<List<ShowSearchWordModel>>(listOf())
    val searchWords = _searchWords.asStateFlow()

    private var _showSearchItems = MutableStateFlow<PagingData<ShowInfoModel>>(PagingData.empty())
    val showSearchItems = _showSearchItems.asStateFlow()

    private var _showItems = MutableStateFlow<PagingData<ShowInfoModel>>(PagingData.empty())
    val showItems = _showItems.asStateFlow()

    init {
        requestShowSearchWords()
        when (uiState.value.genre) {
            ShowGenreType.PLAY -> loadPlayItems()
            ShowGenreType.MUSICAL -> loadMusicalItems()
        }
    }

    override fun reduceState(currentState: ShowSearchUiState, event: ShowSearchEvent): ShowSearchUiState =
        when (event) {
            ShowSearchEvent.ActivateSearch -> currentState.copy(isActiveSearch = true)
            ShowSearchEvent.DeActivateSearch -> currentState.copy(isActiveSearch = false)
            ShowSearchEvent.Searching -> currentState.copy(isDoneSearch = false)
            ShowSearchEvent.DoneSearch -> currentState.copy(isDoneSearch = true)
            is ShowSearchEvent.SetQueryString -> currentState.copy(queryString = event.query)
            is ShowSearchEvent.ChangeGenre -> currentState.copy(genre = event.genre)
            is ShowSearchEvent.ChangeSort -> currentState.copy(sortType = event.sortType)
        }

    fun searchShowList(query: String) {
        showRepository.fetchSearchShowList(query)
            .cachedIn(viewModelScope)
            .onEach { _showSearchItems.value = it }
            .launchIn(viewModelScope)
    }

    fun onActiveSearch() {
        sendAction(ShowSearchEvent.ActivateSearch)
    }

    fun onDeActiveSearch() {
        sendAction(ShowSearchEvent.DeActivateSearch)
    }

    fun onDoneSearch() {
        sendAction(ShowSearchEvent.DoneSearch)
    }

    fun onSearching() {
        sendAction(ShowSearchEvent.Searching)
    }

    fun setQueryString(query: String = "") {
        sendAction(ShowSearchEvent.SetQueryString(query))
    }

    fun changeGenre(genre: ShowGenreType) {
        sendAction(ShowSearchEvent.ChangeGenre(genre))
        if (genre == ShowGenreType.PLAY) {
            loadPlayItems()
        } else {
            loadMusicalItems()
        }
    }

    fun changeSortType(sortType: ShowSortType) {
        sendAction(
            ShowSearchEvent.ChangeSort(
                sortType = sortType
            )
        )
        when (uiState.value.genre) {
            ShowGenreType.PLAY -> {
                loadPlayItems()
            }

            ShowGenreType.MUSICAL -> {
                loadMusicalItems()
            }
        }
    }

    private fun loadPlayItems() {
        showRepository.fetchShowList(ShowGenreType.PLAY.name, uiState.value.sortType.code)
            .cachedIn(viewModelScope)
            .onEach { _showItems.value = it }
            .launchIn(viewModelScope)
    }

    private fun loadMusicalItems() {
        showRepository.fetchShowList(ShowGenreType.MUSICAL.name, uiState.value.sortType.code)
            .cachedIn(viewModelScope)
            .onEach { _showItems.value = it }
            .launchIn(viewModelScope)
    }

    fun requestFavoriteShow(showId: String) {
        favoriteRepository.requestFavoriteShow(showId).launchIn(viewModelScope)
    }

    fun deleteFavoriteShow(showId: String) {
        favoriteRepository.deleteFavoriteShow(showId).launchIn(viewModelScope)
    }

    private fun requestShowSearchWords() {
        showRepository.getShowSearchWordList()
            .onEach { _searchWords.value = it }
            .launchIn(viewModelScope)
    }

    fun insertShowSearchWord(query: String) {
        viewModelScope.launch {
            showRepository.insertShowSearchWord(
                showSearchWordModel = ShowSearchWordModel(query, System.currentTimeMillis())
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
