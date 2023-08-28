package com.cmc.curtaincall.core.network.service.review

import com.cmc.curtaincall.core.network.service.review.request.CreateShowReviewRequest
import com.cmc.curtaincall.core.network.service.review.request.UpdateShowReviewRequest
import com.cmc.curtaincall.core.network.service.review.response.CreateReviewResponse
import com.cmc.curtaincall.core.network.service.review.response.LikeReviewsResponse
import com.cmc.curtaincall.core.network.service.review.response.ShowReviewsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ReviewService {

    @POST("shows/{showId}/reviews")
    suspend fun createShowReview(
        @Path("showId") showId: String,
        @Body createShowReviewRequest: CreateShowReviewRequest
    ): CreateReviewResponse

    @GET("shows/{showId}/reviews?")
    suspend fun requestShowReviewList(
        @Path("showId") showId: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): ShowReviewsResponse

    @DELETE("reviews/{reviewId}")
    suspend fun deleteShowReview(
        @Path("reviewId") reviewId: Int
    ): Response<Unit>

    @PATCH("reviews/{reviewId}")
    suspend fun updateShowReview(
        @Path("reviewId") reviewId: Int,
        @Body updateShowReviewRequest: UpdateShowReviewRequest
    ): Response<Unit>

    @PUT("reviews/{reviewId}/like")
    suspend fun requestLikeReview(
        @Path("reviewId") reviewId: Int
    ): Response<Unit>

    @DELETE("reviews/{reviewId}/like")
    suspend fun requestDislikeReview(
        @Path("reviewId") reviewId: Int
    ): Response<Unit>

    @GET("member/like")
    suspend fun checkLikeReviews(
        @Query("reviewIds") reviewIds: List<Int>
    ): LikeReviewsResponse
}
