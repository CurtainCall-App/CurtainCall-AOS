package com.cmc.curtaincall.core.network.service.show.response

import com.cmc.curtaincall.domain.model.show.ShowRecommendationModel
import com.google.gson.annotations.SerializedName

data class ShowRecommendationsResponse(
    @SerializedName("content") val showRecommendations: List<ShowRecommendationResponse>
)

data class ShowRecommendationResponse(
    val id: Int,
    val description: String,
    val showId: String,
    val name: String,
    val genre: String,
    val startDate: String,
    val endDate: String,
    val poster: String?
) {
    fun toModel() = ShowRecommendationModel(
        id = id,
        description = description,
        showId = showId,
        name = name,
        genre = genre,
        startDate = startDate,
        endDate = endDate,
        poster = poster
    )
}
