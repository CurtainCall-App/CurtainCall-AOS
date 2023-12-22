package com.cmc.curtaincall.domain.model.lostproperty

data class LostPropertyDetailModel(
    val facilityId: String = "",
    val facilityName: String = "",
    val facilityPhone: String = "",
    val foundDate: String = "",
    val foundPlaceDetail: String = "",
    val foundTime: String? = null,
    val id: Int = Int.MIN_VALUE,
    val imageId: Int = Int.MIN_VALUE,
    val imageUrl: String = "",
    val particulars: String = "",
    val title: String = "",
    val type: String = ""
)
