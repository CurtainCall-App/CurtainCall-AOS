package com.cmc.curtaincall.feature.show.search

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.cmc.curtaincall.core.base.BaseViewModel
import com.cmc.curtaincall.domain.model.show.ShowInfoModel
import com.cmc.curtaincall.domain.model.show.ShowSearchWordModel
import com.cmc.curtaincall.domain.repository.FavoriteRepository
import com.cmc.curtaincall.domain.repository.LaunchRepository
import com.cmc.curtaincall.domain.repository.ShowRepository
import com.cmc.curtaincall.domain.type.ShowGenreType
import com.cmc.curtaincall.domain.type.ShowSortType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowListViewModel @Inject constructor(
    private val showRepository: ShowRepository,
    private val favoriteRepository: FavoriteRepository,
    private val launchRepository: LaunchRepository
) : BaseViewModel<ShowSearchUiState, ShowSearchEvent, Nothing>(
    initialState = ShowSearchUiState()
) {
    private val _sortType = MutableStateFlow(ShowSortType.REVIEW_GRADE)
    val sortType = _sortType.asStateFlow()

    private val _genreType = MutableStateFlow(ShowGenreType.PLAY)
    val genreType = _genreType.asStateFlow()

    private var _showInfoModels = MutableStateFlow<PagingData<ShowInfoModel>>(PagingData.empty())
    val showInfoModels = _showInfoModels.asStateFlow()

    // PagingData 갱신 여부
    private val _isRefresh = MutableSharedFlow<Boolean>()
    val isRefresh = _isRefresh.asSharedFlow()

    // 최초 작품 진입 여부
    private val _isFirstEntry = MutableStateFlow(false)
    val isFirstEntry = _isFirstEntry.asStateFlow()

    // //

    private var _searchWords = MutableStateFlow<List<ShowSearchWordModel>>(listOf())
    val searchWords = _searchWords.asStateFlow()

    private var _showSearchItems = MutableStateFlow<PagingData<ShowInfoModel>>(PagingData.empty())
    val showSearchItems = _showSearchItems.asStateFlow()

    init {
        checkIsFirstEntry()
        requestShowSearchWords()
        when (uiState.value.genre) {
            ShowGenreType.PLAY -> loadPlayItems()
            ShowGenreType.MUSICAL -> loadMusicalItems()
        }
    }

    private fun checkIsFirstEntry() {
        launchRepository.getIsFirstEntryShowList()
            .onEach { _isFirstEntry.value = it }
            .launchIn(viewModelScope)
    }

    fun setFirstEntry() {
        viewModelScope.launch {
            launchRepository.setIsFirstEntryShowList()
        }
    }

    fun selectGenreType(genre: ShowGenreType) {
        if (_genreType.value != genre) {
            _genreType.value = genre
            if (genre == ShowGenreType.PLAY) {
                fetchPlayPagingData()
            } else {
                fetchMusicalPagingData()
            }
        }
    }

    fun checkShowLike(
        showId: String,
        isLike: Boolean
    ) {
        if (isLike) {
            favoriteRepository.requestFavoriteShow(showId)
                .onEach { _isRefresh.emit(true) }
                .launchIn(viewModelScope)
        } else {
            favoriteRepository.deleteFavoriteShow(showId)
                .onEach { _isRefresh.emit(true) }
                .launchIn(viewModelScope)
        }
    }

    fun fetchShowList() {
        showRepository.fetchShowList(genreType.value.name, sortType.value.code)
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .onEach { _showInfoModels.value = it }
            .launchIn(viewModelScope)
    }

    private fun fetchPlayPagingData() {
        showRepository.fetchShowList(ShowGenreType.PLAY.name, sortType.value.code)
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .onEach { _showInfoModels.value = it }
            .launchIn(viewModelScope)
    }

    private fun fetchMusicalPagingData() {
        showRepository.fetchShowList(ShowGenreType.MUSICAL.name, sortType.value.code)
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .onEach { _showInfoModels.value = it }
            .launchIn(viewModelScope)
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
            .onEach { _showInfoModels.value = it }
            .launchIn(viewModelScope)
    }

    private fun loadMusicalItems() {
        showRepository.fetchShowList(ShowGenreType.MUSICAL.name, uiState.value.sortType.code)
            .cachedIn(viewModelScope)
            .onEach { _showInfoModels.value = it }
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
