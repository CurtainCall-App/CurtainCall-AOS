package com.cmc.curtaincall.core.network.service.favorite.response

import com.cmc.curtaincall.core.network.service.show.response.ShowTimeResponse
import com.cmc.curtaincall.domain.model.favorite.FavoriteShowModel
import com.google.gson.annotations.SerializedName

data class FavoriteShowsResponse(
    @SerializedName("content") val favoriteShows: List<FavoriteShowResponse>
)

data class FavoriteShowResponse(
    val id: String = "",
    val name: String = "",
    val startDate: String = "",
    val endDate: String = "",
    val facilityName: String = "",
    val poster: String = "",
    val genre: String = "",
    val showTimes: List<ShowTimeResponse> = listOf(),
    val reviewCount: Int = 1,
    val reviewGradeSum: Int = 0,
    val runtime: String = ""
) {
    fun toModel() = FavoriteShowModel(
        id = id,
        name = name,
        startDate = startDate,
        endDate = endDate,
        facilityName = facilityName,
        poster = poster,
        genre = genre,
        showTimes = showTimes.map { it.toModel() },
        reviewCount = reviewCount,
        reviewGradeSum = reviewGradeSum,
        runtime = runtime
    )
}
