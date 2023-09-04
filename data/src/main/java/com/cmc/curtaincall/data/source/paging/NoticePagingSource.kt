package com.cmc.curtaincall.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cmc.curtaincall.core.network.service.notice.NoticeService
import com.cmc.curtaincall.core.network.service.notice.response.NoticeResponse
import javax.inject.Inject

private const val NOTICE_STARTING_KEY = 0
const val NOTICE_PAGE_SIZE = 20

class NoticePagingSource @Inject constructor(
    private val noticeService: NoticeService
) : PagingSource<Int, NoticeResponse>() {
    override fun getRefreshKey(state: PagingState<Int, NoticeResponse>): Int? {
        return state.anchorPosition?.let { position ->
            val anchorPage = state.closestPageToPosition(position)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NoticeResponse> {
        try {
            val pageKey = params.key ?: NOTICE_STARTING_KEY
            val response = noticeService.requestNoticeList(
                page = pageKey,
                size = NOTICE_PAGE_SIZE
            )
            return LoadResult.Page(
                response.notices,
                prevKey = null,
                nextKey = if (response.notices.isEmpty()) null else pageKey + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}
