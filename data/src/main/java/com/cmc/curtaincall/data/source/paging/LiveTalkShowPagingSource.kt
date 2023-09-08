package com.cmc.curtaincall.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cmc.curtaincall.core.network.service.show.ShowService
import com.cmc.curtaincall.core.network.service.show.response.LiveTalkShowResponse
import javax.inject.Inject

private const val LIVETALK_SHOW_STARTING_KEY = 0
const val LIVETALK_SHOW_PAGE_SIZE = 20

class LiveTalkShowPagingSource @Inject constructor(
    private val showService: ShowService,
    private val baseDateTime: String
) : PagingSource<Int, LiveTalkShowResponse>() {
    override fun getRefreshKey(state: PagingState<Int, LiveTalkShowResponse>): Int? {
        return state.anchorPosition?.let { position ->
            val anchorPage = state.closestPageToPosition(position)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LiveTalkShowResponse> {
        try {
            val pageKey = params.key ?: LIVETALK_SHOW_STARTING_KEY
            val response = showService.requestLiveTalkShowList(
                page = pageKey,
                size = LIVETALK_SHOW_PAGE_SIZE,
                baseDateTime = baseDateTime
            )

            return LoadResult.Page(
                response.liveTalks,
                prevKey = null,
                nextKey = if (response.liveTalks.isEmpty()) null else pageKey + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}
