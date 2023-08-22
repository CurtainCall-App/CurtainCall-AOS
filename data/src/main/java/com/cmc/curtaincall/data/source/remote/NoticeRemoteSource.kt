package com.cmc.curtaincall.data.source.remote

import com.cmc.curtaincall.core.network.service.notice.NoticeService
import com.cmc.curtaincall.core.network.service.notice.response.NoticeDetailResponse
import com.cmc.curtaincall.core.network.service.notice.response.NoticeResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NoticeRemoteSource @Inject constructor(
    private val noticeService: NoticeService
) {

    fun requestNoticeList(
        page: Int,
        size: Int
    ): Flow<List<NoticeResponse>> = flow {
        emit(
            noticeService.requestNoticeList(
                page = page,
                size = size
            ).notices
        )
    }

    fun requestNoticeDetail(
        noticeId: Int
    ): Flow<NoticeDetailResponse> = flow {
        emit(
            noticeService.requestNoticeDetail(noticeId)
        )
    }
}