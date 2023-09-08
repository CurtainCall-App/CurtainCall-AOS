package com.cmc.curtaincall.core.network.service.show.response

import com.cmc.curtaincall.domain.model.show.LiveTalkShowModel
import com.google.gson.annotations.SerializedName

data class LiveTalkShowsResponse(
    @SerializedName("content") val liveTalks: List<LiveTalkShowResponse>
)

data class LiveTalkShowResponse(
    val id: String,
    val name: String,
    val facilityId: String,
    val facilityName: String,
    val poster: String,
    val genre: String,
    val showAt: String,
    val showEndAt: String
) {
    fun toModel() = LiveTalkShowModel(
        id = id,
        name = name,
        facilityId = facilityId,
        facilityName = facilityName,
        poster = poster,
        genre = genre,
        showAt = showAt,
        showEndAt = showEndAt
    )
}
