package com.cmc.curtaincall.core.network.service.report

data class ReportRequest(
    val partyId: Int,
    val reason: String,
    val content: String
)
