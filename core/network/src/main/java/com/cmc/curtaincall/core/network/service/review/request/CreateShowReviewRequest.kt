package com.cmc.curtaincall.core.network.service.review.request

data class CreateShowReviewRequest(
    val showId: String,
    val grade: Int,
    val content: String
)
