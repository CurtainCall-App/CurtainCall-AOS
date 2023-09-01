package com.cmc.curtaincall.data.source.remote

import com.cmc.curtaincall.core.network.service.favorite.FavoriteService
import com.cmc.curtaincall.core.network.service.favorite.response.CheckFavoriteShowResponse
import com.cmc.curtaincall.core.network.service.favorite.response.FavoriteShowResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavoriteRemoteSource @Inject constructor(
    private val favoriteService: FavoriteService
) {
    fun requestFavoriteShow(
        showId: String
    ): Flow<Boolean> = flow {
        emit(
            favoriteService.requestFavoriteShow(showId).isSuccessful
        )
    }

    fun deleteFavoriteShow(
        showId: String
    ): Flow<Boolean> = flow {
        emit(
            favoriteService.deleteFavoriteShow(showId).isSuccessful
        )
    }

    fun checkFavoriteShows(
        showIds: List<String>
    ): Flow<List<CheckFavoriteShowResponse>> = flow {
        emit(
            favoriteService.checkFavoriteShows(showIds).checkFavoriteShows
        )
    }

    fun requestFavoriteShows(
        memberId: Int
    ): Flow<List<FavoriteShowResponse>> = flow {
        emit(
            favoriteService.requestFavoriteShows(memberId).favoriteShows
        )
    }
}
