package com.cmc.curtaincall.domain.model.review

data class ShowReviewModel(
    val content: String = "",
    val createdAt: String = "",
    val creatorId: Int = 0,
    val creatorImageUrl: String? = null,
    val creatorNickname: String = "",
    val grade: Int = 0,
    val id: Int = 0,
    val showId: String = "",
    val likeCount: Int = 0,
    val isFavortie: Boolean = false
)
