package com.cmc.curtaincall.data.source.remote

import com.cmc.curtaincall.core.network.service.review.ReviewService
import com.cmc.curtaincall.core.network.service.review.request.CreateShowReviewRequest
import com.cmc.curtaincall.core.network.service.review.request.UpdateShowReviewRequest
import com.cmc.curtaincall.core.network.service.review.response.CreateReviewResponse
import com.cmc.curtaincall.core.network.service.review.response.LikeReviewResponse
import com.cmc.curtaincall.core.network.service.review.response.ShowReviewResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ReviewRemoteSource @Inject constructor(
    private val reviewService: ReviewService
) {
    fun createShowReview(
        showId: String,
        grade: Int,
        content: String
    ): Flow<CreateReviewResponse> = flow {
        emit(
            reviewService.createShowReview(
                showId = showId,
                createShowReviewRequest = CreateShowReviewRequest(
                    grade = grade,
                    content = content
                )
            )
        )
    }

    fun requestShowReviewList(
        showId: String,
        page: Int,
        size: Int
    ): Flow<List<ShowReviewResponse>> = flow {
        emit(
            reviewService.requestShowReviewList(
                showId = showId,
                page = page,
                size = size
            ).showReviews
        )
    }

    fun deleteShowReview(
        reviewId: String
    ): Flow<Boolean> = flow {
        emit(
            reviewService.deleteShowReview(reviewId).isSuccessful
        )
    }

    fun updateShowReview(
        reviewId: String,
        content: String,
        grade: Int
    ): Flow<Boolean> = flow {
        emit(
            reviewService.updateShowReview(
                reviewId = reviewId,
                updateShowReviewRequest = UpdateShowReviewRequest(
                    content = content,
                    grade = grade
                )
            ).isSuccessful
        )
    }

    fun requestLikeReview(
        reviewId: String
    ): Flow<Boolean> = flow {
        emit(
            reviewService.requestLikeReview(reviewId).isSuccessful
        )
    }

    fun requestDislikeReview(
        reviewId: String
    ): Flow<Boolean> = flow {
        emit(
            reviewService.requestDislikeReview(reviewId).isSuccessful
        )
    }

    fun checkLikeReviews(
        reviewIds: List<String>
    ): Flow<List<LikeReviewResponse>> = flow {
        emit(
            reviewService.checkLikeReviews(reviewIds).likeReviews
        )
    }
}
