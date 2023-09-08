package com.cmc.curtaincall.core.network.service.report

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ReportService {

    @POST("reports")
    suspend fun requestReport(
        @Body reportRequest: ReportRequest
    ): Response<Unit>
}
