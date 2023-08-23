package com.cmc.curtaincall.feature.performance.detail

import androidx.lifecycle.viewModelScope
import com.cmc.curtaincall.core.base.BaseViewModel
import com.cmc.curtaincall.domain.repository.ShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PerformanceDetailViewModel @Inject constructor(
    private val showRepository: ShowRepository
) : BaseViewModel<PerformanceDetailState, PerformanceDetailEvent, Nothing>(
    initialState = PerformanceDetailState()
) {
    override fun reduceState(currentState: PerformanceDetailState, event: PerformanceDetailEvent): PerformanceDetailState =
        when (event) {
            is PerformanceDetailEvent.RequestShowDetail -> {
                currentState.copy(showDetailModel = event.showDetailModel)
            }

            is PerformanceDetailEvent.RequestFacilityDetail -> {
                currentState.copy(facilityDetailModel = event.facilityDetailModel)
            }
        }

    fun requestShowDetail(showId: String) {
        showRepository.requestShowDetail(showId)
            .onEach { sendAction(PerformanceDetailEvent.RequestShowDetail(it)) }
            .flatMapLatest { showRepository.requestFacilityDetail(it.facilityId) }
            .onEach { sendAction(PerformanceDetailEvent.RequestFacilityDetail(it)) }
            .launchIn(viewModelScope)
    }
}
