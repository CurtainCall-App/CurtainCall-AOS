package com.cmc.curtaincall.domain.repository

import androidx.paging.PagingData
import com.cmc.curtaincall.domain.model.notice.NoticeDetailModel
import com.cmc.curtaincall.domain.model.notice.NoticeModel
import kotlinx.coroutines.flow.Flow

interface NoticeRepository {
    fun requestNoticeList(): Flow<PagingData<NoticeModel>>

    fun requestNoticeDetail(
        noticeId: Int
    ): Flow<NoticeDetailModel>
}
