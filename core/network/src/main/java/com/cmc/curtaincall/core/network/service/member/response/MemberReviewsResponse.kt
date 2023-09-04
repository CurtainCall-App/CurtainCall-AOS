package com.cmc.curtaincall.core.network.service.member.response

import com.cmc.curtaincall.domain.model.member.MemberReviewModel
import com.google.gson.annotations.SerializedName

data class MemberReviewsResponse(
    @SerializedName("content") val reviews: List<MemberReviewResponse>
)

data class MemberReviewResponse(
    val id: Int,
    val showId: String,
    val showName: String,
    val grade: Int,
    val content: String,
    val createdAt: String,
    val likeCount: Int
) {
    fun toModel() = MemberReviewModel(
        id = id,
        showId = showId,
        showName = showName,
        grade = grade,
        content = content,
        createdAt = createdAt,
        likeCount = likeCount
    )
}
