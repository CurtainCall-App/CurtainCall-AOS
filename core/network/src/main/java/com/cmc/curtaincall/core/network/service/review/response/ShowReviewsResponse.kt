package com.cmc.curtaincall.core.network.service.review.response

import com.cmc.curtaincall.domain.model.review.ShowReviewModel
import com.google.gson.annotations.SerializedName

data class ShowReviewsResponse(
    @SerializedName("content") val showReviews: List<ShowReviewResponse>
)

data class ShowReviewResponse(
    val content: String,
    val createdAt: String,
    val creatorId: Int,
    val creatorImageUrl: String?,
    val creatorNickname: String,
    val grade: Int,
    val id: Int,
    val showId: String
) {
    fun toModel() = ShowReviewModel(
        content = content,
        createdAt = createdAt,
        creatorId = creatorId,
        creatorImageUrl = creatorImageUrl,
        creatorNickname = creatorNickname,
        grade = grade,
        id = id,
        showId = showId
    )
}
