package com.cmc.curtaincall.core.network.service.member.response

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
)
