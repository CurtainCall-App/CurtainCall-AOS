package com.cmc.curtaincall.feature.home.report

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
) : ViewModel() {

    private val _reportReason = MutableStateFlow(HomeReportReason.NONE)
    val reportReason = _reportReason.asStateFlow()

    private val _content = MutableStateFlow("")
    val content = _content.asStateFlow()

    private val _isSuccessReport = MutableStateFlow(false)
    val isSuccessReport = _isSuccessReport.asStateFlow()

    fun changeReportReason(reportReason: HomeReportReason) {
        _reportReason.value = reportReason
    }

    fun setContent(content: String) {
        _content.value = content
    }

    fun requestReport(
        reportId: Int,
        type: ReportType
    ) {
        reportRepository.requestReport(
            reportId = reportId,
            type = type.name,
            reason = reportReason.value.name,
            content = content.value
        ).onEach { check ->
            _isSuccessReport.value = check
        }.launchIn(viewModelScope)
    }
}
