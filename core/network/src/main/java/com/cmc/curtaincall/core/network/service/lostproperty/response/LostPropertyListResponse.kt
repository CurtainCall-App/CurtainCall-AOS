package com.cmc.curtaincall.core.network.service.lostproperty.response

import com.cmc.curtaincall.domain.model.lostproperty.LostPropertyModel
import com.google.gson.annotations.SerializedName

data class LostItemListResponse(
    @SerializedName("content") val lostItems: List<LostItemResponse>
)

data class LostItemResponse(
    val id: Int,
    val facilityId: String,
    val facilityName: String,
    val title: String,
    val foundDate: String,
    val foundTime: String?,
    val imageUrl: String
) {
    fun toModel() = LostPropertyModel(
        id = id,
        facilityId = facilityId,
        facilityName = facilityName,
        title = title,
        foundDate = foundDate,
        foundTime = foundTime,
        imageUrl = imageUrl
    )
}
