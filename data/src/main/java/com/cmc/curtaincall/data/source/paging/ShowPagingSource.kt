package com.cmc.curtaincall.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cmc.curtaincall.core.network.service.show.ShowService
import com.cmc.curtaincall.core.network.service.show.response.ShowInfoResponse
import javax.inject.Inject

private const val SHOW_STARTING_KEY = 0
const val SHOW_PAGE_SIZE = 20

class ShowPagingSource @Inject constructor(
    private val showService: ShowService,
    private val genre: String
) : PagingSource<Int, ShowInfoResponse>() {
    override fun getRefreshKey(state: PagingState<Int, ShowInfoResponse>): Int? {
        return state.anchorPosition?.let { position ->
            val anchorPage = state.closestPageToPosition(position)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ShowInfoResponse> {
        try {
            val pageKey = params.key ?: SHOW_STARTING_KEY
            val response = showService.requestShowList(
                page = pageKey,
                size = SHOW_PAGE_SIZE,
                genre = genre
            )
            return LoadResult.Page(
                response.showInfos,
                prevKey = null,
                nextKey = if (response.showInfos.isEmpty()) null else pageKey + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}
