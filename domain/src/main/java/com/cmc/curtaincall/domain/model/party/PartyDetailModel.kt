package com.cmc.curtaincall.domain.model.party

data class PartyDetailModel(
    val category: String,
    val content: String,
    val createdAt: String,
    val creatorId: Int,
    val creatorImageUrl: String,
    val creatorNickname: String,
    val curMemberNum: Int,
    val facilityId: String,
    val facilityName: String,
    val id: Int,
    val maxMemberNum: Int,
    val showAt: String,
    val showId: String,
    val showName: String,
    val title: String
)
