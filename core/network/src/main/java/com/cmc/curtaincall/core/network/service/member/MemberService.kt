package com.cmc.curtaincall.core.network.service.member

import com.cmc.curtaincall.core.network.service.member.request.MemberCreateRequest
import com.cmc.curtaincall.core.network.service.member.response.MemberCreateResponse
import com.cmc.curtaincall.core.network.service.member.response.MemberDuplicateNickNameResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
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
}
