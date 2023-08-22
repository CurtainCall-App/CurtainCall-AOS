package com.cmc.curtaincall.core.network.service.notice

import com.cmc.curtaincall.core.network.service.notice.response.NoticeDetailResponse
import com.cmc.curtaincall.core.network.service.notice.response.NoticeListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NoticeService {

    @GET("notices")
    suspend fun requestNoticeList(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): NoticeListResponse

    @GET("notices/{noticeId}")
    suspend fun requestNoticeDetail(
        @Path("noticeId") noticeId: Int
    ): NoticeDetailResponse
}