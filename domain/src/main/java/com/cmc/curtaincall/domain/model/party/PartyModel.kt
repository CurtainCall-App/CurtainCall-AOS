package com.cmc.curtaincall.domain.model.party

data class PartyModel(
    val category: String = "",
    val createdAt: String = "",
    val creatorId: Int = Int.MIN_VALUE,
    val creatorImageUrl: String? = null,
    val creatorNickname: String = "",
    val curMemberNum: Int = Int.MIN_VALUE,
    val facilityId: String = "",
    val facilityName: String = "",
    val id: Int = Int.MIN_VALUE,
    val maxMemberNum: Int = Int.MAX_VALUE,
    val showAt: String = "",
    val showId: String = "",
    val showName: String = "",
    val showPoster: String = "",
    val title: String = ""
)
