package com.cmc.curtaincall.feature.home.report

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmc.curtaincall.domain.repository.ReportRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

enum class ReportReason(val value: String) {
    SPAM("스팸홍보/도배글입니다."),
    HATE_SPEECH("욕설/혐오/차별적 표현입니다."),
    ILLEGAL("불법정보를 포함하고 있습니다."),
    BAD_MANNERS("비매너 사용자입니다."),
    HARMFUL_TO_TEENAGER("청소년에게 유해한 내용입니다."),
    PERSONAL_INFORMATION_DISCLOSURE("개인정보 노출 게시물입니다."),
    ETC("다른 문제가 있습니다."),
    NONE("")
}

@HiltViewModel
class HomeReportViewModel @Inject constructor(
    private val reportRepository: ReportRepository
) : ViewModel() {

    private val _reportReason = MutableStateFlow(ReportReason.NONE)
    val reportReason = _reportReason.asStateFlow()

    private val _content = MutableStateFlow("")
    val content = _content.asStateFlow()

    private val _isSuccessReport = MutableStateFlow(false)
    val isSuccessReport = _isSuccessReport.asStateFlow()

    fun changeReportReason(reportReason: ReportReason) {
        _reportReason.value = reportReason
    }

    fun setContent(content: String) {
        _content.value = content
    }

    fun requestReport(
        reportId: Int,
        type: String
    ) {
        reportRepository.requestReport(
            reportId = reportId,
            type = type,
            reason = reportReason.value.name,
            content = content.value
        ).onEach { check ->
            _isSuccessReport.value = check
        }.launchIn(viewModelScope)
    }
}
