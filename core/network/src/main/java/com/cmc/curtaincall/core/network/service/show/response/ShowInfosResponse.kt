package com.cmc.curtaincall.core.network.service.show.response

import com.cmc.curtaincall.domain.model.show.ShowInfoModel
import com.google.gson.annotations.SerializedName

data class ShowInfosResponse(
    @SerializedName("content") val showInfos: List<ShowInfoResponse>
)

data class ShowInfoResponse(
    val endDate: String,
    val facilityName: String,
    val genre: String,
    val id: String,
    val name: String,
    val poster: String,
    val reviewCount: Int,
    val reviewGradeSum: Int,
    val runtime: String,
    val showTimes: List<ShowTimeResponse>,
    val startDate: String
) {
    fun toModel() = ShowInfoModel(
        endDate = endDate,
        facilityName = facilityName,
        genre = genre,
        id = id,
        name = name,
        poster = poster,
        reviewCount = reviewCount,
        reviewGradeSum = reviewGradeSum,
        runtime = runtime,
        showTimes = showTimes.map { it.toModel() },
        startDate = startDate
    )
}
