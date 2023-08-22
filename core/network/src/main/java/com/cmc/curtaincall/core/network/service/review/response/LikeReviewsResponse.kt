package com.cmc.curtaincall.core.network.service.review.response

import com.cmc.curtaincall.domain.model.review.LikeReviewModel
import com.google.gson.annotations.SerializedName

data class LikeReviewsResponse(
    @SerializedName("content") val likeReviews: List<LikeReviewResponse>
)

data class LikeReviewResponse(
    val liked: Boolean,
    val showReviewId: Int
) {
    fun toModel() = LikeReviewModel(
        liked = liked,
        showReviewId = showReviewId
    )
}