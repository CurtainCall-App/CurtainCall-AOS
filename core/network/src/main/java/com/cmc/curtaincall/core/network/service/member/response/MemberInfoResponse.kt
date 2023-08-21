package com.cmc.curtaincall.core.network.service.member.response

import com.cmc.curtaincall.domain.model.home.MemberInfoModel

data class MemberInfoResponse(
    val id: Int? = null,
    val nickname: String,
    val imageUrl: String,
    val recruitingNum: Int,
    val participationNum: Int
) {
    fun toModel() = MemberInfoModel(
        id = id,
        nickname = nickname,
        imageUrl = imageUrl
    )
}
