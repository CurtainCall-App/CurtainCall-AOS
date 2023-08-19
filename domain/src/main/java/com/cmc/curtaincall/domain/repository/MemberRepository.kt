package com.cmc.curtaincall.domain.repository

import kotlinx.coroutines.flow.Flow

interface MemberRepository {

    fun checkDuplicateNickname(nickname: String): Flow<Boolean>

    fun createMember(nickname: String): Flow<Int>
}
