package com.cmc.curtaincall.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cmc.curtaincall.core.network.service.member.MemberService
import com.cmc.curtaincall.core.network.service.member.response.MemberReviewResponse
import javax.inject.Inject

private const val MY_REVIEW_STARTING_KEY = 0
const val MY_REVIEW_PAGE_SIZE = 20

class MyReviewPagingSource @Inject constructor(
    private val memberService: MemberService
) : PagingSource<Int, MemberReviewResponse>() {
    override fun getRefreshKey(state: PagingState<Int, MemberReviewResponse>): Int? {
        return state.anchorPosition?.let { position ->
            val anchorPage = state.closestPageToPosition(position)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MemberReviewResponse> {
        try {
            val pageKey = params.key ?: MY_REVIEW_STARTING_KEY
            val response = memberService.requestMyReviews(
                page = pageKey,
                size = MY_REVIEW_PAGE_SIZE
            )
            return LoadResult.Page(
                response.reviews,
                prevKey = null,
                nextKey = if (response.reviews.isEmpty()) null else pageKey + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}
