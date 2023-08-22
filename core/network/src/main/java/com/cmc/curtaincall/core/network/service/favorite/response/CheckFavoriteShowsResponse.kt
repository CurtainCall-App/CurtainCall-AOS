package com.cmc.curtaincall.core.network.service.favorite.response

import com.cmc.curtaincall.domain.model.favorite.CheckFavoriteShowModel
import com.google.gson.annotations.SerializedName

data class CheckFavoriteShowsResponse(
    @SerializedName("content") val checkFavoriteShows: List<CheckFavoriteShowResponse>
)

data class CheckFavoriteShowResponse(
    val favorite: Boolean,
    val showId: String
) {
    fun toModel() = CheckFavoriteShowModel(
        favorite = favorite,
        showId = showId
    )
}
