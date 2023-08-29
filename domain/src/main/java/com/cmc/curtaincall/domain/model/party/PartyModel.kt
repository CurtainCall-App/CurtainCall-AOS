package com.cmc.curtaincall.domain.model.party

data class PartyModel(
    val category: String = "",
    val createdAt: String = "",
    val creatorId: Int = Int.MIN_VALUE,
    val creatorImageUrl: String? = null,
    val creatorNickname: String = "",
    val curMemberNum: Int = Int.MIN_VALUE,
    val facilityId: String? = null,
    val facilityName: String? = null,
    val id: Int = Int.MIN_VALUE,
    val maxMemberNum: Int = Int.MAX_VALUE,
    val showAt: String? = null,
    val showId: String? = null,
    val showName: String? = null,
    val showPoster: String? = null,
    val title: String = "",
    val isParticipation: Boolean = false
)
