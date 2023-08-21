package com.cmc.curtaincall.data.repository

import com.cmc.curtaincall.data.source.local.MemberLocalSource
import com.cmc.curtaincall.data.source.remote.MemberRemoteSource
import com.cmc.curtaincall.domain.model.home.MemberInfoModel
import com.cmc.curtaincall.domain.model.home.MyParticipationModel
import com.cmc.curtaincall.domain.model.home.MyRecruitmentModel
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

    override fun requestMemberInfo(memberId: Int): Flow<MemberInfoModel> =
        memberRemoteSource.requestMemberInfo(memberId)

    override fun requestMyRecruitments(
        memberId: Int,
        page: Int,
        size: Int,
        category: String
    ): Flow<List<MyRecruitmentModel>> =
        memberRemoteSource.requestMyRecruitments(
            memberId = memberId,
            page = page,
            size = size,
            category = category
        )

    override fun requestMyParticipations(
        memberId: Int,
        page: Int,
        size: Int,
        category: String
    ): Flow<List<MyParticipationModel>> =
        memberRemoteSource.requestMyParticipations(
            memberId = memberId,
            page = page,
            size = size,
            category = category
        )

    override suspend fun saveMemberId(id: Int) =
        memberLocalSource.saveMemberId(id)

    override suspend fun saveMemberNickname(nickname: String) =
        memberLocalSource.saveMemberNickname(nickname)

    override fun getMemberId(): Flow<Int> =
        memberLocalSource.getMemberId()

    override fun getMemberNickname(): Flow<String> =
        memberLocalSource.getMemberNickname()
}
