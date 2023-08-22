package com.cmc.curtaincall.domain.repository

import kotlinx.coroutines.flow.Flow

interface ReportRepository {

    fun requestReport(
        partyId: Int,
        reason: String,
        content: String
    ): Flow<Boolean>
}