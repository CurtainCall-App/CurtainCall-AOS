package com.cmc.curtaincall.core.network.service.review.response

import com.cmc.curtaincall.domain.model.review.CreateReviewModel

data class CreateReviewResponse(
    val id: Int
) {
    fun toModel() = CreateReviewModel(id = id)
}
