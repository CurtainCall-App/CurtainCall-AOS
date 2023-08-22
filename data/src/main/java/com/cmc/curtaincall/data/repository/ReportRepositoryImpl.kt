package com.cmc.curtaincall.data.repository

import com.cmc.curtaincall.data.source.remote.ReportRemoteSource
import com.cmc.curtaincall.domain.repository.ReportRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReportRepositoryImpl @Inject constructor(
    private val reportRemoteSource: ReportRemoteSource
) : ReportRepository {
    override fun requestReport(partyId: Int, reason: String, content: String): Flow<Boolean> =
        reportRemoteSource.requestReport(
            partyId = partyId,
            reason = reason,
            content = content
        )
}
