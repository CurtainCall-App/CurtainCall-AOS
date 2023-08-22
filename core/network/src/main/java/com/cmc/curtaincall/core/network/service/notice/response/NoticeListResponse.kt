package com.cmc.curtaincall.core.network.service.notice.response

import com.cmc.curtaincall.domain.model.notice.NoticeModel
import com.google.gson.annotations.SerializedName

data class NoticeListResponse(
    @SerializedName("content") val notices: List<NoticeResponse>
)

data class NoticeResponse(
    val createdAt: String,
    val id: Int,
    val title: String
) {
    fun toModel() = NoticeModel(
        createdAt = createdAt,
        id = id,
        title = title
    )
}
