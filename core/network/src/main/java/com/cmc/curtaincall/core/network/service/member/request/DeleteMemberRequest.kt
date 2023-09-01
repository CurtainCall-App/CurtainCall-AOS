package com.cmc.curtaincall.core.network.service.member.request

data class DeleteMemberRequest(
    val reason: String,
    val content: String
)
