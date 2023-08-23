package com.cmc.curtaincall.domain.repository

import androidx.paging.PagingData
import com.cmc.curtaincall.domain.model.review.CreateReviewModel
import com.cmc.curtaincall.domain.model.review.LikeReviewModel
import com.cmc.curtaincall.domain.model.review.ShowReviewModel
import kotlinx.coroutines.flow.Flow

interface ReviewRepository {

    fun createShowReview(
        showId: String,
        grade: Int,
        content: String
    ): Flow<CreateReviewModel>

    fun fetchShowReviewList(
        showId: String
    ): Flow<PagingData<ShowReviewModel>>

    fun requestShowReviewList(
        showId: String,
        page: Int,
        size: Int
    ): Flow<List<ShowReviewModel>>

    fun deleteShowReview(
        reviewId: String
    ): Flow<Boolean>

    fun updateShowReview(
        reviewId: String,
        content: String,
        grade: Int
    ): Flow<Boolean>

    fun requestLikeReview(
        reviewId: String
    ): Flow<Boolean>

    fun requestDislikeReview(
        reviewId: String
    ): Flow<Boolean>

    fun checkLikeReviews(
        reviewIds: List<String>
    ): Flow<List<LikeReviewModel>>
}
