package com.cmc.curtaincall.domain.repository

import com.cmc.curtaincall.domain.model.home.MemberInfoModel
import com.cmc.curtaincall.domain.model.home.MyParticipationsModel
import com.cmc.curtaincall.domain.model.home.MyRecruitmentModel
import kotlinx.coroutines.flow.Flow

interface MemberRepository {

    fun checkDuplicateNickname(nickname: String): Flow<Boolean>

    fun createMember(nickname: String): Flow<Int>

    fun requestMemberInfo(memberId: Int): Flow<MemberInfoModel>

    fun requestMyRecruitments(memberId: Int): Flow<MyRecruitmentModel>

    fun requestMyParticipations(memberId: Int): Flow<MyParticipationsModel>

    suspend fun saveMemberId(id: Int)

    suspend fun saveMemberNickname(nickname: String)

    fun getMemberId(): Flow<Int>

    fun getMemberNickname(): Flow<String>
}
