package com.cmc.curtaincall.core.network.service.lostitem.response

import com.cmc.curtaincall.domain.model.lostItem.LostItemDetailModel

data class LostItemDetailResponse(
    val facilityId: String,
    val facilityName: String,
    val facilityPhone: String,
    val foundDate: String,
    val foundPlaceDetail: String,
    val foundTime: String,
    val id: Int,
    val imageId: Int,
    val imageUrl: String,
    val particulars: String,
    val title: String,
    val type: String
) {
    fun toModel() = LostItemDetailModel(
        facilityId = facilityId,
        facilityName = facilityName,
        facilityPhone = facilityPhone,
        foundDate = foundDate,
        foundPlaceDetail = foundPlaceDetail,
        foundTime = foundTime,
        id = id,
        imageId = imageId,
        imageUrl = imageUrl,
        particulars = particulars,
        title = title,
        type = type
    )
}
