package com.cmc.curtaincall.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cmc.curtaincall.core.network.service.review.ReviewService
import com.cmc.curtaincall.domain.model.review.ShowReviewModel
import javax.inject.Inject

private const val REVIEW_STARTING_KEY = 0
const val REVIEW_PAGE_SIZE = 20

class ReviewPagingSource @Inject constructor(
    private val reviewService: ReviewService,
    private val showId: String
) : PagingSource<Int, ShowReviewModel>() {
    override fun getRefreshKey(state: PagingState<Int, ShowReviewModel>): Int? {
        return state.anchorPosition?.let { position ->
            val anchorPage = state.closestPageToPosition(position)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ShowReviewModel> {
        try {
            val pageKey = params.key ?: REVIEW_STARTING_KEY
            val response = reviewService.requestShowReviewList(
                showId = showId,
                page = pageKey,
                size = REVIEW_PAGE_SIZE
            )
            val favoriteReviews = reviewService.checkLikeReviews(response.showReviews.map { it.id })
            val result = response.showReviews.map { showReview ->
                showReview.toModel().copy(
                    isFavortie = favoriteReviews.likeReviews.find {
                        it.showReviewId == showReview.id
                    }?.liked ?: false
                )
            }
            return LoadResult.Page(
                result,
                prevKey = null,
                nextKey = if (result.isEmpty()) null else pageKey + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}
