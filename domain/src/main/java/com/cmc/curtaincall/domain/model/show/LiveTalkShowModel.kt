package com.cmc.curtaincall.domain.model.show

data class LiveTalkShowModel(
    val id: String,
    val name: String,
    val facilityId: String,
    val facilityName: String,
    val poster: String,
    val genre: String,
    val showAt: String,
    val showEndAt: String
)
