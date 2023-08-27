package com.cmc.curtaincall.core.network.service.lostitem

import com.cmc.curtaincall.core.network.service.lostitem.request.CreateLostItemRequest
import com.cmc.curtaincall.core.network.service.lostitem.request.UpdateLostItemRequest
import com.cmc.curtaincall.core.network.service.lostitem.response.CreateLostItemResponse
import com.cmc.curtaincall.core.network.service.lostitem.response.LostItemDetailResponse
import com.cmc.curtaincall.core.network.service.lostitem.response.LostItemListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface LostItemService {

    @POST("lostItems")
    suspend fun createLostItem(
        @Body createLostItemRequest: CreateLostItemRequest
    ): CreateLostItemResponse

    @GET("lostItems")
    suspend fun requestLostItemList(
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("facilityId") facilityId: String,
        @Query("type") type: String?,
        @Query("foundDate") foundDate: String?,
        @Query("title") title: String?
    ): LostItemListResponse

    @GET("lostItems/{lostItemId}")
    suspend fun requestLostItemDetail(
        @Path("lostItemId") lostItemId: Int
    ): LostItemDetailResponse

    @DELETE("lostItems/{lostItemId}")
    suspend fun deleteLostItem(
        @Path("lostItemId") lostItemId: Int
    ): Response<Unit>

    @PATCH("lostItems/{lostItemId}")
    suspend fun updateLostItem(
        @Path("lostItemId") lostItemId: Int,
        @Body updateLostItemRequest: UpdateLostItemRequest
    ): Response<Unit>
}
