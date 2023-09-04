package com.cmc.curtaincall.core.network.service.member.response

import com.cmc.curtaincall.domain.model.member.MemberLostItemModel
import com.google.gson.annotations.SerializedName

data class MemberLostItemsResponse(
    @SerializedName("content") val lostItems: List<MemberLostItemResponse>
)

data class MemberLostItemResponse(
    val id: Int,
    val facilityId: String,
    val facilityName: String,
    val title: String,
    val foundDate: String,
    val foundTime: String?,
    val type: String,
    val imageUrl: String,
    val createdAt: String
) {
    fun toModel() = MemberLostItemModel(
        id = id,
        facilityId = facilityId,
        facilityName = facilityName,
        title = title,
        foundDate = foundDate,
        foundTime = foundTime,
        type = type,
        imageUrl = imageUrl,
        createdAt = createdAt
    )
}
