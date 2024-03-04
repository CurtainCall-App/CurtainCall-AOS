package com.cmc.curtaincall.domain.repository

import androidx.paging.PagingData
import com.cmc.curtaincall.domain.enums.ReviewSortType
import com.cmc.curtaincall.domain.model.review.CreateReviewModel
import com.cmc.curtaincall.domain.model.review.LikeReviewModel
import com.cmc.curtaincall.domain.model.review.ShowReviewModel
import com.cmc.curtaincall.domain.model.show.CheckCreateReviewModel
import kotlinx.coroutines.flow.Flow

interface ReviewRepository {

    fun createShowReview(
        showId: String,
        grade: Int,
        content: String
    ): Flow<CreateReviewModel>

    fun fetchShowReviewList(
        showId: String,
        sortType: ReviewSortType
    ): Flow<PagingData<ShowReviewModel>>

    fun requestShowReviewList(
        showId: String,
        page: Int,
        size: Int,
        sortType: ReviewSortType
    ): Flow<List<ShowReviewModel>>

    fun deleteShowReview(
        reviewId: Int
    ): Flow<Boolean>

    fun updateShowReview(
        reviewId: Int,
        content: String,
        grade: Int
    ): Flow<Boolean>

    fun checkCreatedReview(
        showId: String
    ): Flow<CheckCreateReviewModel>

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
