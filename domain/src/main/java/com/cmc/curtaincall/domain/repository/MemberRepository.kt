package com.cmc.curtaincall.domain.repository

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
        category: String
    ): Flow<List<MyRecruitmentModel>>

    fun requestMyParticipations(
        memberId: Int,
        page: Int,
        size: Int,
        category: String
    ): Flow<List<MyParticipationModel>>

    suspend fun saveMemberId(id: Int)

    suspend fun saveMemberNickname(nickname: String)

    fun getMemberId(): Flow<Int>

    fun getMemberNickname(): Flow<String>
}
