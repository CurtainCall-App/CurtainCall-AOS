package com.cmc.curtaincall.core.network.service.review.response

import com.cmc.curtaincall.domain.model.show.CheckCreateReviewModel

data class CheckCreateReviewResponse(
    val id: Int,
    val showId: String,
    val grade: Int,
    val content: String,
    val creatorId: Int,
    val creatorNickname: String,
    val creatorImageUrl: String?,
    val createdAt: String,
    val likeCount: Int
) {
    fun toModel() = CheckCreateReviewModel(
        id = id,
        showId = showId,
        grade = grade,
        content = content,
        creatorId = creatorId,
        creatorNickname = creatorNickname,
        creatorImageUrl = creatorImageUrl,
        createdAt = createdAt,
        likeCount = likeCount
    )
}
