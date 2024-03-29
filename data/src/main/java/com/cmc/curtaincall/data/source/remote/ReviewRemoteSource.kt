package com.cmc.curtaincall.data.source.remote

import com.cmc.curtaincall.core.network.service.review.ReviewService
import com.cmc.curtaincall.core.network.service.review.request.CreateShowReviewRequest
import com.cmc.curtaincall.core.network.service.review.request.UpdateShowReviewRequest
import com.cmc.curtaincall.core.network.service.review.response.CreateReviewResponse
import com.cmc.curtaincall.core.network.service.review.response.LikeReviewResponse
import com.cmc.curtaincall.core.network.service.review.response.ShowReviewResponse
import com.cmc.curtaincall.domain.enums.ReviewSortType
import com.cmc.curtaincall.domain.model.show.CheckCreateReviewModel
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
                createShowReviewRequest = CreateShowReviewRequest(
                    showId = showId,
                    grade = grade,
                    content = content
                )
            )
        )
    }

    fun requestShowReviewList(
        showId: String,
        page: Int,
        size: Int,
        sortType: ReviewSortType
    ): Flow<List<ShowReviewResponse>> = flow {
        emit(
            reviewService.requestShowReviewList(
                showId = showId,
                page = page,
                size = size,
                sort = sortType.code
            ).showReviews
        )
    }

    fun deleteShowReview(
        reviewId: Int
    ): Flow<Boolean> = flow {
        emit(
            reviewService.deleteShowReview(reviewId).isSuccessful
        )
    }

    fun updateShowReview(
        reviewId: Int,
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

    fun checkCreatedReview(
        showId: String
    ): Flow<CheckCreateReviewModel> = flow {
        emit(reviewService.checkCreatedReview(showId).toModel())
    }

    fun requestLikeReview(
        reviewId: Int
    ): Flow<Boolean> = flow {
        emit(
            reviewService.requestLikeReview(reviewId).isSuccessful
        )
    }

    fun requestDislikeReview(
        reviewId: Int
    ): Flow<Boolean> = flow {
        emit(
            reviewService.requestDislikeReview(reviewId).isSuccessful
        )
    }

    fun checkLikeReviews(
        reviewIds: List<Int>
    ): Flow<List<LikeReviewResponse>> = flow {
        emit(
            reviewService.checkLikeReviews(reviewIds).likeReviews
        )
    }
}
