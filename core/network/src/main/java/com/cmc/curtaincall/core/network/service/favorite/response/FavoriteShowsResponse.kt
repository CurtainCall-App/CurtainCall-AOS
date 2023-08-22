package com.cmc.curtaincall.core.network.service.favorite.response

import com.cmc.curtaincall.domain.model.favorite.FavoriteShowModel
import com.google.gson.annotations.SerializedName

data class FavoriteShowsResponse(
    @SerializedName("content") val favoriteShows: List<FavoriteShowResponse>
)

data class FavoriteShowResponse(
    val id: String,
    val name: String,
    val poster: String,
    val reviewCount: Int,
    val reviewGradeSum: Int,
    val story: String
) {
    fun toModel() = FavoriteShowModel(
        id = id,
        name = name,
        poster = poster,
        reviewCount = reviewCount,
        reviewGradeSum = reviewGradeSum,
        story = story
    )
}
