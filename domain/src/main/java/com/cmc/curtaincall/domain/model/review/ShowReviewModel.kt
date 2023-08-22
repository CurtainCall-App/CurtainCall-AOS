package com.cmc.curtaincall.domain.model.review

data class ShowReviewModel(
    val content: String,
    val createdAt: Any,
    val creatorId: Int,
    val creatorImageUrl: String,
    val creatorNickname: String,
    val grade: Int,
    val id: Int,
    val showId: String
)