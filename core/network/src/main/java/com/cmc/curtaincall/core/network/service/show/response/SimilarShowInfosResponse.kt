package com.cmc.curtaincall.core.network.service.show.response

import com.cmc.curtaincall.domain.model.show.SimilarShowInfoModel
import com.google.gson.annotations.SerializedName

data class SimilarShowInfosResponse(
    @SerializedName("content") val similarShowInfos: List<SimilarShowInfoResponse>
)

data class SimilarShowInfoResponse(
    val id: String,
    val name: String,
    val startDate: String,
    val endDate: String,
    val facilityName: String,
    val poster: String,
    val genre: String,
    val showTimes: List<ShowTimeResponse>,
    val reviewCount: Int,
    val reviewGradeSum: Int,
    val runtime: String
) {
    fun toModel() = SimilarShowInfoModel(
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
