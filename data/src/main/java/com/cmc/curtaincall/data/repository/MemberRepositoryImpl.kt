package com.cmc.curtaincall.data.repository

import com.cmc.curtaincall.data.source.remote.MemberRemoteSource
import com.cmc.curtaincall.domain.repository.MemberRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MemberRepositoryImpl @Inject constructor(
    private val memberRemoteSource: MemberRemoteSource
) : MemberRepository {
    override fun checkDuplicateNickname(nickname: String): Flow<Boolean> =
        memberRemoteSource.checkDuplicateNickname(nickname)

    override fun createMember(nickname: String): Flow<Int> =
        memberRemoteSource.createMember(nickname)
}
