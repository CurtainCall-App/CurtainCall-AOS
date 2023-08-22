package com.cmc.curtaincall.domain.repository

import com.cmc.curtaincall.domain.model.notice.NoticeDetailModel
import com.cmc.curtaincall.domain.model.notice.NoticeModel
import kotlinx.coroutines.flow.Flow

interface NoticeRepository {
    fun requestNoticeList(
        page: Int,
        size: Int
    ): Flow<List<NoticeModel>>

    fun requestNoticeDetail(
        noticeId: Int
    ): Flow<NoticeDetailModel>
}
