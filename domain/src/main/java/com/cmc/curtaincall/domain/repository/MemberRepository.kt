package com.cmc.curtaincall.domain.repository

import androidx.paging.PagingData
import com.cmc.curtaincall.domain.model.home.MemberInfoModel
import com.cmc.curtaincall.domain.model.home.MyParticipationModel
import com.cmc.curtaincall.domain.model.home.MyRecruitmentModel
import kotlinx.coroutines.flow.Flow

interface MemberRepository {

    fun checkDuplicateNickname(nickname: String): Flow<Boolean>

    fun createMember(nickname: String): Flow<Int>

    fun requestMemberInfo(memberId: Int): Flow<MemberInfoModel>

    fun requestMyRecruitments(
        memberId: Int,
        page: Int,
        size: Int,
        category: String?
    ): Flow<List<MyRecruitmentModel>>

    fun fetchMyRecruitments(
        memberId: Int,
        category: String
    ): Flow<PagingData<MyRecruitmentModel>>

    fun requestMyParticipations(
        memberId: Int,
        page: Int,
        size: Int,
        category: String?
    ): Flow<List<MyParticipationModel>>

    fun fetchMyParticipations(
        memberId: Int,
        category: String
    ): Flow<PagingData<MyParticipationModel>>

    fun deleteMember(
        reason: String,
        content: String
    ): Flow<Boolean>

    suspend fun saveMemberId(id: Int)

    suspend fun saveMemberNickname(nickname: String)

    fun getMemberId(): Flow<Int>

    fun getMemberNickname(): Flow<String>
}
