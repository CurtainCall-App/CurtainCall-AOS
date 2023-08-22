package com.cmc.curtaincall.core.network.service.show.response

import com.cmc.curtaincall.domain.model.show.ShowRankModel

data class ShowRanksResponse(
    val showRanks: List<ShowRankResponse>
)

data class ShowRankResponse(
    val endDate: String,
    val genre: String,
    val id: String,
    val name: String,
    val poster: String,
    val rank: Int,
    val reviewCount: Int,
    val reviewGradeSum: Int,
    val startDate: String
) {
    fun toModel() = ShowRankModel(
        endDate = endDate,
        genre = genre,
        id = id,
        name = name,
        poster = poster,
        rank = rank,
        reviewCount = reviewCount,
        reviewGradeSum = reviewGradeSum,
        startDate = startDate
    )
}