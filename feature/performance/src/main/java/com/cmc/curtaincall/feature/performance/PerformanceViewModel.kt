package com.cmc.curtaincall.feature.performance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.cmc.curtaincall.domain.model.show.ShowInfoModel
import com.cmc.curtaincall.domain.repository.ShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PerformanceViewModel @Inject constructor(
    private val showRepository: ShowRepository
) : ViewModel() {

    private var _isFirstType = MutableStateFlow(true)
    val isFirstType = _isFirstType.asStateFlow()

    val playItems: Flow<PagingData<ShowInfoModel>> =
        showRepository.fetchShowList("PLAY")
            .cachedIn(viewModelScope)

    val musicalItems: Flow<PagingData<ShowInfoModel>> =
        showRepository.fetchShowList("MUSICAL")
            .cachedIn(viewModelScope)

    fun changeSortType(isFirstType: Boolean) {
        _isFirstType.value = isFirstType
    }
}