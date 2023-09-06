package com.cmc.curtaincall.core.network.service.report

data class ReportRequest(
    val idToReport: Int,
    val type: String,
    val reason: String,
    val content: String
)
