package com.cmc.curtaincall.data.repository

import com.cmc.curtaincall.data.source.remote.NoticeRemoteSource
import com.cmc.curtaincall.domain.model.notice.NoticeDetailModel
import com.cmc.curtaincall.domain.model.notice.NoticeModel
import com.cmc.curtaincall.domain.repository.NoticeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoticeRepositoryImpl @Inject constructor(
    private val noticeRemoteSource: NoticeRemoteSource
) : NoticeRepository {
    override fun requestNoticeList(page: Int, size: Int): Flow<List<NoticeModel>> =
        noticeRemoteSource.requestNoticeList(page, size).map { notices ->
            notices.map { it.toModel() }
        }

    override fun requestNoticeDetail(noticeId: Int): Flow<NoticeDetailModel> =
        noticeRemoteSource.requestNoticeDetail(noticeId).map { it.toModel() }
}
