package com.cmc.curtaincall.core.network.service.notice.response

import com.cmc.curtaincall.domain.model.notice.NoticeDetailModel

data class NoticeDetailResponse(
    val content: String,
    val createdAt: String,
    val id: Int,
    val title: String
) {
    fun toModel() = NoticeDetailModel(
        content = content,
        createdAt = createdAt,
        id = id,
        title = title
    )
}
