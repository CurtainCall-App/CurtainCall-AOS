package com.cmc.curtaincall.core.network.service.lostitem.request

data class UpdateLostItemRequest(
    val title: String,
    val type: String,
    val foundPlaceDetail: String,
    val foundDate: String,
    val foundTime: String?,
    val particulars: String,
    val imageId: Int
)
