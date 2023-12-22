package com.cmc.curtaincall.feature.home.report

import com.cmc.curtaincall.core.base.BaseEvent

sealed class HomeReportEvent : BaseEvent {
    object NextStep : HomeReportEvent()
    object PrevStep : HomeReportEvent()

    data class SelectReportReason(
        val reportReason: HomeReportReason
    ) : HomeReportEvent()

    data class InputContent(
        val content: String
    ) : HomeReportEvent()
}
