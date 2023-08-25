package com.cmc.curtaincall.feature.performance

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.cmc.curtaincall.core.base.BaseViewModel
import com.cmc.curtaincall.domain.model.show.ShowSearchWordModel
import com.cmc.curtaincall.domain.repository.ShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PerformanceViewModel @Inject constructor(
    private val showRepository: ShowRepository
) : BaseViewModel<PerformanceState, PerformanceEvent, Nothing>(
    initialState = PerformanceState()
) {

    private var _searchWords = MutableStateFlow<List<ShowSearchWordModel>>(listOf())
    val searchWords = _searchWords.asStateFlow()

    init {
        requestShowSearchWords()
        loadPlayItems()
        loadMusicalItems()
    }

    override fun reduceState(currentState: PerformanceState, event: PerformanceEvent): PerformanceState =
        when (event) {
            is PerformanceEvent.ChangeLastIndex -> {
                currentState.copy(lastIndex = event.lastIndex)
            }

            is PerformanceEvent.ChangeGenre -> {
                currentState.copy(genre = event.genre)
            }

            is PerformanceEvent.ChangeActiveSearch -> {
                currentState.copy(isActiveSearch = event.isActiveSearch)
            }

            is PerformanceEvent.ChangeDoneSearch -> {
                currentState.copy(isDoneSearch = event.isDoneSearch)
            }

            is PerformanceEvent.SetQueryString -> {
                currentState.copy(queryString = event.queryString)
            }

            is PerformanceEvent.SearchShowList -> {
                currentState.copy(showSearchItems = event.showSearchItems)
            }

            is PerformanceEvent.LoadPlayItems -> {
                currentState.copy(playItems = event.playItems)
            }

            is PerformanceEvent.LoadMusicalItems -> {
                currentState.copy(musicalItems = event.musicalItems)
            }
        }

    fun changeLastIndex(lastIndex: Int) {
        sendAction(PerformanceEvent.ChangeLastIndex(lastIndex))
    }

    fun changeGenre(genre: String) {
        sendAction(PerformanceEvent.ChangeGenre(genre))
    }

    fun changeActiveSearch(isActive: Boolean) {
        sendAction(PerformanceEvent.ChangeActiveSearch(isActive))
    }

    fun changeDoneSearch(isDone: Boolean) {
        sendAction(PerformanceEvent.ChangeDoneSearch(isDone))
    }

    fun setQueryString(query: String) {
        sendAction(PerformanceEvent.SetQueryString(query))
    }

    fun searchShowList(query: String) {
        sendAction(
            PerformanceEvent.SearchShowList(
                showSearchItems = showRepository
                    .fetchSearchShowList(query)
                    .cachedIn(viewModelScope)
            )
        )
    }

    private fun loadPlayItems() {
        sendAction(
            PerformanceEvent.LoadPlayItems(
                playItems = showRepository.fetchShowList(
                    "PLAY"
                ).cachedIn(viewModelScope)
            )
        )
    }

    private fun loadMusicalItems() {
        sendAction(
            PerformanceEvent.LoadMusicalItems(
                musicalItems = showRepository.fetchShowList(
                    "MUSICAL"
                ).cachedIn(viewModelScope)
            )
        )
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
