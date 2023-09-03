package com.cmc.curtaincall.domain.model.member

data class MemberReviewModel(
    val id: Int,
    val showId: String,
    val showName: String,
    val grade: Int,
    val content: String,
    val createdAt: String,
    val likeCount: Int
)
