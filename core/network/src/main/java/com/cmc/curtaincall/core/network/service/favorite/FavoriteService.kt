package com.cmc.curtaincall.core.network.service.favorite

import com.cmc.curtaincall.core.network.service.favorite.response.CheckFavoriteShowsResponse
import com.cmc.curtaincall.core.network.service.favorite.response.FavoriteShowsResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface FavoriteService {

    @PUT("shows/{showId}/favorite")
    suspend fun requestFavoriteShow(
        @Path("showId") showId: String
    ): Response<Unit>

    @DELETE("shows/{showId}/favorite")
    suspend fun deleteFavoriteShow(
        @Path("showId") showId: String
    ): Response<Unit>

    @GET("member/favorite")
    suspend fun checkFavoriteShows(
        @Query("showIds") showIds: List<String>
    ): CheckFavoriteShowsResponse

    @GET("member/{memberId}/favorite")
    suspend fun requestFavoriteShows(
        @Path("memberId") memberId: String
    ): FavoriteShowsResponse
}
