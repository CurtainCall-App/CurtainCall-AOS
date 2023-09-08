package com.cmc.curtaincall.domain.repository

import kotlinx.coroutines.flow.Flow

interface ReportRepository {

    fun requestReport(
        reportId: Int,
        type: String,
        reason: String,
        content: String
    ): Flow<Boolean>
}
