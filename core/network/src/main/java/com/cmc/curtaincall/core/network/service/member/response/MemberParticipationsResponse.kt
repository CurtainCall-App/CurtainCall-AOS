package com.cmc.curtaincall.core.network.service.member.response

import com.cmc.curtaincall.domain.model.home.MyParticipationModel
import com.google.gson.annotations.SerializedName

data class MemberParticipationsResponse(
    @SerializedName("content") val participations: List<MemberParticipationResponse>
)

data class MemberParticipationResponse(
    val category: String,
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
    val showPoster: String,
    val title: String
) {
    fun toModel() = MyParticipationModel(
        id = id,
        title = title,
        curMemberNum = curMemberNum,
        maxMemberNum = maxMemberNum,
        showAt = showAt,
        createdAt = createdAt,
        category = category,
        creatorId = creatorId,
        creatorNickname = creatorNickname,
        creatorImageUrl = creatorImageUrl,
        showId = showId,
        showName = showName,
        showPoster = showPoster,
        facilityId = facilityId,
        facilityName = facilityName
    )
}