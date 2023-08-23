package com.cmc.curtaincall.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.cmc.curtaincall.core.network.service.review.ReviewService
import com.cmc.curtaincall.data.source.paging.REVIEW_PAGE_SIZE
import com.cmc.curtaincall.data.source.paging.ReviewPagingSource
import com.cmc.curtaincall.data.source.remote.ReviewRemoteSource
import com.cmc.curtaincall.domain.model.review.CreateReviewModel
import com.cmc.curtaincall.domain.model.review.LikeReviewModel
import com.cmc.curtaincall.domain.model.review.ShowReviewModel
import com.cmc.curtaincall.domain.repository.ReviewRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ReviewRepositoryImpl @Inject constructor(
    private val reviewRemoteSource: ReviewRemoteSource,
    private val reviewService: ReviewService
) : ReviewRepository {
    override fun createShowReview(showId: String): Flow<CreateReviewModel> =
        reviewRemoteSource.createShowReview(showId).map { it.toModel() }

    override fun fetchShowReviewList(showId: String): Flow<PagingData<ShowReviewModel>> {
        return Pager(
            config = PagingConfig(pageSize = REVIEW_PAGE_SIZE),
            pagingSourceFactory = { ReviewPagingSource(reviewService, showId) }
        ).flow
            .map { pagingData ->
                pagingData.map { response ->
                    response.toModel()
                }
            }
    }

    override fun requestShowReviewList(showId: String, page: Int, size: Int): Flow<List<ShowReviewModel>> =
        reviewRemoteSource.requestShowReviewList(showId, page, size).map { showReviews ->
            showReviews.map { it.toModel() }
        }

    override fun deleteShowReview(reviewId: String): Flow<Boolean> =
        reviewRemoteSource.deleteShowReview(reviewId)

    override fun updateShowReview(reviewId: String, content: String, grade: Int): Flow<Boolean> =
        reviewRemoteSource.updateShowReview(reviewId, content, grade)

    override fun requestLikeReview(reviewId: String): Flow<Boolean> =
        reviewRemoteSource.requestLikeReview(reviewId)

    override fun requestDislikeReview(reviewId: String): Flow<Boolean> =
        reviewRemoteSource.requestDislikeReview(reviewId)

    override fun checkLikeReviews(reviewIds: List<String>): Flow<List<LikeReviewModel>> =
        reviewRemoteSource.checkLikeReviews(reviewIds).map { likeReviews ->
            likeReviews.map { it.toModel() }
        }
}
