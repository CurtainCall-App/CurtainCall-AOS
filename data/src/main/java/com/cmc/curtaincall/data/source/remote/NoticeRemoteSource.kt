package com.cmc.curtaincall.data.source.remote

import com.cmc.curtaincall.core.network.service.notice.NoticeService
import com.cmc.curtaincall.core.network.service.notice.response.NoticeDetailResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NoticeRemoteSource @Inject constructor(
    private val noticeService: NoticeService
) {
    fun requestNoticeDetail(
        noticeId: Int
    ): Flow<NoticeDetailResponse> = flow {
        emit(
            noticeService.requestNoticeDetail(noticeId)
        )
    }
}
