package com.cmc.curtaincall.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cmc.curtaincall.core.network.service.lostitem.LostItemService
import com.cmc.curtaincall.core.network.service.lostitem.response.LostItemResponse
import javax.inject.Inject

private const val LOSTITEM_STARTING_KEY = 0
const val LOSTITEM_PAGE_SIZE = 20

class LostItemPagingSource @Inject constructor(
    val lostItemService: LostItemService,
    val facilityId: String,
    val type: String?,
    val foundDate: String?,
    val title: String?
) : PagingSource<Int, LostItemResponse>() {
    override fun getRefreshKey(state: PagingState<Int, LostItemResponse>): Int? {
        return state.anchorPosition?.let { position ->
            val anchorPage = state.closestPageToPosition(position)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LostItemResponse> {
        try {
            val pageKey = params.key ?: LOSTITEM_STARTING_KEY
            val response = lostItemService.requestLostItemList(
                page = pageKey,
                size = LOSTITEM_PAGE_SIZE,
                facilityId = facilityId,
                type = type,
                foundDate = foundDate,
                title = title
            )

            return LoadResult.Page(
                response.lostItems,
                prevKey = null,
                nextKey = if (response.lostItems.isEmpty()) null else pageKey + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}
