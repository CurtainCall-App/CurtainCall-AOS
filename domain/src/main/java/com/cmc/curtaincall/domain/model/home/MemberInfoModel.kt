package com.cmc.curtaincall.domain.model.home

data class MemberInfoModel(
    val id: Int = Int.MIN_VALUE,
    val nickname: String = "",
    val imageId: String? = null,
    val imageUrl: String? = null,
    val recruitingNum: Int = Int.MIN_VALUE,
    val participationNum: Int = Int.MIN_VALUE
)
