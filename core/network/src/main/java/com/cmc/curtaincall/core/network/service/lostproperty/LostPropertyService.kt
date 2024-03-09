package com.cmc.curtaincall.core.network.service.lostproperty

import com.cmc.curtaincall.core.network.service.lostproperty.request.CreateLostPropertyRequest
import com.cmc.curtaincall.core.network.service.lostproperty.request.UpdateLostPropertyRequest
import com.cmc.curtaincall.core.network.service.lostproperty.response.CreateLostPropertyResponse
import com.cmc.curtaincall.core.network.service.lostproperty.response.LostItemListResponse
import com.cmc.curtaincall.core.network.service.lostproperty.response.LostPropertyDetailResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface LostPropertyService {

    @POST("lostItems")
    suspend fun createLostItem(
        @Body createLostItemRequest: CreateLostPropertyRequest
    ): CreateLostPropertyResponse

    @GET("lostItems")
    suspend fun requestLostItemList(
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("facilityId") facilityId: String,
        @Query("foundDateStart") foundDateStart: String?,
        @Query("foundDateEnd") foundDateEnd: String?
    ): LostItemListResponse

    @GET("lostItems/{lostItemId}")
    suspend fun requestLostItemDetail(
        @Path("lostItemId") lostItemId: Int
    ): LostPropertyDetailResponse

    @DELETE("lostItems/{lostItemId}")
    suspend fun deleteLostItem(
        @Path("lostItemId") lostItemId: Int
    ): Response<Unit>

    @PATCH("lostItems/{lostItemId}")
    suspend fun updateLostItem(
        @Path("lostItemId") lostItemId: Int,
        @Body updateLostItemRequest: UpdateLostPropertyRequest
    ): Response<Unit>
}
