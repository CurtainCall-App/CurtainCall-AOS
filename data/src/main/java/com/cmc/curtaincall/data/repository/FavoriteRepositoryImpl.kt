package com.cmc.curtaincall.data.repository

import com.cmc.curtaincall.data.source.remote.FavoriteRemoteSource
import com.cmc.curtaincall.domain.model.favorite.CheckFavoriteShowModel
import com.cmc.curtaincall.domain.model.favorite.FavoriteShowModel
import com.cmc.curtaincall.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteRemoteSource: FavoriteRemoteSource
) : FavoriteRepository {
    override fun requestFavoriteShow(showId: String): Flow<Boolean> =
        favoriteRemoteSource.requestFavoriteShow(showId)

    override fun deleteFavoriteShow(showId: String): Flow<Boolean> =
        favoriteRemoteSource.deleteFavoriteShow(showId)

    override fun checkFavoriteShows(showIds: List<String>): Flow<List<CheckFavoriteShowModel>> =
        favoriteRemoteSource.checkFavoriteShows(showIds).map { checkFavoriteShows ->
            checkFavoriteShows.map { it.toModel() }
        }

    override fun requestFavoriteShows(memberId: Int): Flow<List<FavoriteShowModel>> =
        favoriteRemoteSource.requestFavoriteShows(memberId).map { favoriteShows ->
            favoriteShows.map { it.toModel() }
        }
}
