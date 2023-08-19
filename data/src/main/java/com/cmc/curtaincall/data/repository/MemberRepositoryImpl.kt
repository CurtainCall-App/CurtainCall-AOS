package com.cmc.curtaincall.data.repository

import com.cmc.curtaincall.data.source.local.MemberLocalSource
import com.cmc.curtaincall.data.source.remote.MemberRemoteSource
import com.cmc.curtaincall.domain.repository.MemberRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MemberRepositoryImpl @Inject constructor(
    private val memberRemoteSource: MemberRemoteSource,
    private val memberLocalSource: MemberLocalSource
) : MemberRepository {
    override fun checkDuplicateNickname(nickname: String): Flow<Boolean> =
        memberRemoteSource.checkDuplicateNickname(nickname)

    override fun createMember(nickname: String): Flow<Int> =
        memberRemoteSource.createMember(nickname)

    override suspend fun saveMemberId(id: Int) =
        memberLocalSource.saveMemberId(id)

    override suspend fun saveMemberNickname(nickname: String) =
        memberLocalSource.saveMemberNickname(nickname)

    override fun getMemberId(): Flow<Int> =
        memberLocalSource.getMemberId()

    override fun getMemberNickname(): Flow<String> =
        memberLocalSource.getMemberNickname()
}
