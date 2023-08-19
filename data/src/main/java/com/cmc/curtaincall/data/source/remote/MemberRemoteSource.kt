package com.cmc.curtaincall.data.source.remote

import com.cmc.curtaincall.core.network.service.member.MemberService
import com.cmc.curtaincall.core.network.service.member.request.MemberCreateRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MemberRemoteSource @Inject constructor(
    private val memberService: MemberService
) {
    fun checkDuplicateNickname(nickname: String): Flow<Boolean> = flow {
        emit(memberService.checkDuplicateNickname(nickname).result)
    }

    fun createMember(nickname: String): Flow<Int> = flow {
        emit(
            memberService.createMember(
                memberCreateRequest = MemberCreateRequest(
                    nickname = nickname
                )
            ).id
        )
    }
}
