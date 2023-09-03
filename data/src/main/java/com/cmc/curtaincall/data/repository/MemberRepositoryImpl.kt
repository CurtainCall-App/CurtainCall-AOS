package com.cmc.curtaincall.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.cmc.curtaincall.core.network.service.member.MemberService
import com.cmc.curtaincall.data.source.local.MemberLocalSource
import com.cmc.curtaincall.data.source.paging.MyParticipationPagingSource
import com.cmc.curtaincall.data.source.paging.MyRecruitmentPagingSource
import com.cmc.curtaincall.data.source.paging.PARTICIPATION_PAGE_SIZE
import com.cmc.curtaincall.data.source.paging.RECRUITMENT_PAGE_SIZE
import com.cmc.curtaincall.data.source.remote.MemberRemoteSource
import com.cmc.curtaincall.domain.model.member.MemberInfoModel
import com.cmc.curtaincall.domain.model.member.MyParticipationModel
import com.cmc.curtaincall.domain.model.member.MyRecruitmentModel
import com.cmc.curtaincall.domain.repository.MemberRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MemberRepositoryImpl @Inject constructor(
    private val memberRemoteSource: MemberRemoteSource,
    private val memberLocalSource: MemberLocalSource,
    private val memberService: MemberService
) : MemberRepository {

    override fun fetchMyRecruitments(memberId: Int, category: String): Flow<PagingData<MyRecruitmentModel>> {
        return Pager(
            config = PagingConfig(pageSize = RECRUITMENT_PAGE_SIZE),
            pagingSourceFactory = { MyRecruitmentPagingSource(memberService, memberId, category) }
        ).flow
            .map { pagingData ->
                pagingData.map { response ->
                    response.toModel()
                }
            }
    }

    override fun fetchMyParticipations(memberId: Int, category: String): Flow<PagingData<MyParticipationModel>> {
        return Pager(
            config = PagingConfig(pageSize = PARTICIPATION_PAGE_SIZE),
            pagingSourceFactory = { MyParticipationPagingSource(memberService, memberId, category) }
        ).flow
            .map { pagingData ->
                pagingData.map { response ->
                    response.toModel()
                }
            }
    }

    override fun checkDuplicateNickname(nickname: String): Flow<Boolean> =
        memberRemoteSource.checkDuplicateNickname(nickname)

    override fun createMember(nickname: String): Flow<Int> =
        memberRemoteSource.createMember(nickname)

    override fun requestMemberInfo(memberId: Int): Flow<MemberInfoModel> =
        memberRemoteSource.requestMemberInfo(memberId)

    override fun updateMember(nickname: String, imageId: Int?): Flow<Boolean> =
        memberRemoteSource.updateMember(nickname, imageId)

    override fun requestMyRecruitments(
        memberId: Int,
        page: Int,
        size: Int,
        category: String?
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
        category: String?
    ): Flow<List<MyParticipationModel>> =
        memberRemoteSource.requestMyParticipations(
            memberId = memberId,
            page = page,
            size = size,
            category = category
        )

    override fun deleteMember(authorization: String, reason: String, content: String): Flow<Boolean> =
        memberRemoteSource.deleteMember(authorization, reason, content)

    override suspend fun saveMemberId(id: Int) =
        memberLocalSource.saveMemberId(id)

    override suspend fun saveMemberNickname(nickname: String) =
        memberLocalSource.saveMemberNickname(nickname)

    override fun getMemberId(): Flow<Int> =
        memberLocalSource.getMemberId()

    override fun getMemberNickname(): Flow<String> =
        memberLocalSource.getMemberNickname()
}
