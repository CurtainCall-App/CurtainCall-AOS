package com.cmc.curtaincall.feature.home.report

import androidx.lifecycle.viewModelScope
import com.cmc.curtaincall.core.base.BaseViewModel
import com.cmc.curtaincall.domain.repository.ReportRepository
import com.cmc.curtaincall.domain.type.ReportType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeReportViewModel @Inject constructor(
    private val reportRepository: ReportRepository
) : BaseViewModel<HomeReportUiState, HomeReportEvent, Nothing>(
    initialState = HomeReportUiState()
) {
    override fun reduceState(currentState: HomeReportUiState, event: HomeReportEvent): HomeReportUiState =
        when (event) {
            HomeReportEvent.NextStep -> currentState.copy(step = currentState.step + 1)
            HomeReportEvent.PrevStep -> currentState.copy(step = currentState.step - 1)
            is HomeReportEvent.InputContent -> currentState.copy(content = event.content)
            is HomeReportEvent.SelectReportReason -> currentState.copy(reportReason = event.reportReason)
        }

    private val _isCompleted = MutableStateFlow(false)
    val isCompleted = _isCompleted.asStateFlow()

    fun goNextStep() {
        sendAction(HomeReportEvent.NextStep)
    }

    fun goPrevStep() {
        sendAction(HomeReportEvent.PrevStep)
    }

    fun changeReportReason(reportReason: HomeReportReason) {
        sendAction(HomeReportEvent.SelectReportReason(reportReason))
    }

    fun setContent(content: String) {
        sendAction(HomeReportEvent.InputContent(content))
    }

    fun requestReport(
        reportId: Int,
        type: ReportType
    ) {
        reportRepository.requestReport(
            reportId = reportId,
            type = type.name,
            reason = uiState.value.reportReason.name,
            content = uiState.value.content
        ).onEach { check ->
            _isCompleted.value = check
        }.launchIn(viewModelScope)
    }
}
