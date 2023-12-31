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
        reviewId: Int
    ): Flow<Boolean>

    fun updateShowReview(
        reviewId: Int,
        content: String,
        grade: Int
    ): Flow<Boolean>

    fun requestLikeReview(
        reviewId: Int
    ): Flow<Boolean>

    fun requestDislikeReview(
        reviewId: Int
    ): Flow<Boolean>

    fun checkLikeReviews(
        reviewIds: List<Int>
    ): Flow<List<LikeReviewModel>>
}
