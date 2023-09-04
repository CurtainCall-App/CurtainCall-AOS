package com.cmc.curtaincall.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cmc.curtaincall.core.network.service.member.MemberService
import com.cmc.curtaincall.core.network.service.member.response.MemberLostItemResponse
import javax.inject.Inject

private const val MY_LOSTITEM_STARTING_KEY = 0
const val MY_LOSTITEM_PAGE_SIZE = 20

class MyLostItemPagingSource @Inject constructor(
    private val memberService: MemberService
) : PagingSource<Int, MemberLostItemResponse>() {
    override fun getRefreshKey(state: PagingState<Int, MemberLostItemResponse>): Int? {
        return state.anchorPosition?.let { position ->
            val anchorPage = state.closestPageToPosition(position)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MemberLostItemResponse> {
        try {
            val pageKey = params.key ?: MY_LOSTITEM_STARTING_KEY
            val response = memberService.requestMyLostItems(
                page = pageKey,
                size = MY_LOSTITEM_PAGE_SIZE
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
