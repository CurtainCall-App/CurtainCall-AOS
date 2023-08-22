package com.cmc.curtaincall.data.source.remote

import com.cmc.curtaincall.core.network.service.report.ReportRequest
import com.cmc.curtaincall.core.network.service.report.ReportService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ReportRemoteSource @Inject constructor(
    private val reportService: ReportService
) {
    fun requestReport(
        partyId: Int,
        reason: String,
        content: String
    ): Flow<Boolean> = flow {
        emit(
            reportService.requestReport(
                reportRequest = ReportRequest(
                    partyId = partyId,
                    reason = reason,
                    content = content
                )
            ).isSuccessful
        )
    }
}
