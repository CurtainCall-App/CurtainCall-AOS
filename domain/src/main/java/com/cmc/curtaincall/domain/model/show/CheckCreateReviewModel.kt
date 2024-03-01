package com.cmc.curtaincall.domain.model.show

data class CheckCreateReviewModel(
    val id: Int = 0,
    val showId: String = "",
    val grade: Int = 0,
    val content: String = "",
    val creatorId: Int = 0,
    val creatorNickname: String = "",
    val creatorImageUrl: String? = null,
    val createdAt: String = "",
    val likeCount: Int = 0
)
