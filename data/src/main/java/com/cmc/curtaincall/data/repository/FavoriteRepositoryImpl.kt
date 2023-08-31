package com.cmc.curtaincall.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.cmc.curtaincall.core.network.service.favorite.FavoriteService
import com.cmc.curtaincall.data.source.paging.FAVORITE_SHOW_PAGE_SIZE
import com.cmc.curtaincall.data.source.paging.FavoriteShowPagingSource
import com.cmc.curtaincall.data.source.remote.FavoriteRemoteSource
import com.cmc.curtaincall.domain.model.favorite.CheckFavoriteShowModel
import com.cmc.curtaincall.domain.model.favorite.FavoriteShowModel
import com.cmc.curtaincall.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteRemoteSource: FavoriteRemoteSource,
    private val favoriteService: FavoriteService
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

    override fun fetchFavoriteShows(memberId: Int): Flow<PagingData<FavoriteShowModel>> {
        return Pager(
            config = PagingConfig(pageSize = FAVORITE_SHOW_PAGE_SIZE),
            pagingSourceFactory = { FavoriteShowPagingSource(favoriteService, memberId) }
        ).flow
    }
}
