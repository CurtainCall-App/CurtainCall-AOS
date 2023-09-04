package com.cmc.curtaincall.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.cmc.curtaincall.core.network.service.notice.NoticeService
import com.cmc.curtaincall.data.source.paging.NOTICE_PAGE_SIZE
import com.cmc.curtaincall.data.source.paging.NoticePagingSource
import com.cmc.curtaincall.data.source.remote.NoticeRemoteSource
import com.cmc.curtaincall.domain.model.notice.NoticeDetailModel
import com.cmc.curtaincall.domain.model.notice.NoticeModel
import com.cmc.curtaincall.domain.repository.NoticeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoticeRepositoryImpl @Inject constructor(
    private val noticeRemoteSource: NoticeRemoteSource,
    private val noticeService: NoticeService
) : NoticeRepository {
    override fun requestNoticeList(): Flow<PagingData<NoticeModel>> {
        return Pager(
            config = PagingConfig(NOTICE_PAGE_SIZE),
            pagingSourceFactory = { NoticePagingSource(noticeService) }
        ).flow
            .map { pagingData ->
                pagingData.map { it.toModel() }
            }
    }

    override fun requestNoticeDetail(noticeId: Int): Flow<NoticeDetailModel> =
        noticeRemoteSource.requestNoticeDetail(noticeId).map { it.toModel() }
}
