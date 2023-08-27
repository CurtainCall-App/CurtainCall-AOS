package com.cmc.curtaincall.feature.performance.lostitem.create

data class PerformanceLostItemCreateUiState(
    val title: String = "",
    val type: String = "",
    val foundPlaceDetail: String = "",
    val foundDate: String = "",
    val foundTime: String? = null,
    val particulars: String = "",
    val imageId: Int = Int.MIN_VALUE
)
