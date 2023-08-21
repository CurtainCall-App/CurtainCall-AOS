package com.cmc.curtaincall.core.network.service.member

import com.cmc.curtaincall.core.network.service.member.request.MemberCreateRequest
import com.cmc.curtaincall.core.network.service.member.response.MemberCreateResponse
import com.cmc.curtaincall.core.network.service.member.response.MemberDuplicateNickNameResponse
import com.cmc.curtaincall.core.network.service.member.response.MemberInfoResponse
import com.cmc.curtaincall.core.network.service.member.response.MemberParticipationsResponse
import com.cmc.curtaincall.core.network.service.member.response.MemberRecruitmentsResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MemberService {

    @GET("members/duplicate/nickname")
    suspend fun checkDuplicateNickname(
        @Query("nickname") nickname: String
    ): MemberDuplicateNickNameResponse

    @POST("signup")
    suspend fun createMember(
        @Body memberCreateRequest: MemberCreateRequest
    ): MemberCreateResponse

    @GET("members/{memberId}")
    suspend fun requestMemberInfo(
        @Path("memberId") memberId: Int
    ): MemberInfoResponse

    @GET("members/{memberId}/recruitments")
    suspend fun requestMyRecruitments(
        @Path("memberId") memberId: Int,
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("category") category: String
    ): MemberRecruitmentsResponse

    @GET("members/{memberId}/participation")
    suspend fun requestMyParticipations(
        @Path("memberId") memberId: Int,
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("category") category: String
    ): MemberParticipationsResponse
}
