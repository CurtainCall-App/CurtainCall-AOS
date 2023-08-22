package com.cmc.curtaincall.domain.repository

import com.cmc.curtaincall.domain.model.favorite.CheckFavoriteShowModel
import com.cmc.curtaincall.domain.model.favorite.FavoriteShowModel
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun requestFavoriteShow(
        showId: String
    ): Flow<Boolean>

    fun deleteFavoriteShow(
        showId: String
    ): Flow<Boolean>

    fun checkFavoriteShows(
        showIds: List<String>
    ): Flow<List<CheckFavoriteShowModel>>

    fun requestFavoriteShows(
        memberId: String
    ): Flow<List<FavoriteShowModel>>
}