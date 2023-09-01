package com.cmc.curtaincall.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cmc.curtaincall.core.network.service.favorite.FavoriteService
import com.cmc.curtaincall.domain.model.favorite.FavoriteShowModel
import javax.inject.Inject

private const val FAVORITE_SHOW_STARTING_KEY = 0
const val FAVORITE_SHOW_PAGE_SIZE = 20

class FavoriteShowPagingSource @Inject constructor(
    private val favoriteService: FavoriteService,
    private val memberId: Int
) : PagingSource<Int, FavoriteShowModel>() {
    override fun getRefreshKey(state: PagingState<Int, FavoriteShowModel>): Int? {
        return state.anchorPosition?.let { position ->
            val anchorPage = state.closestPageToPosition(position)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FavoriteShowModel> {
        try {
            val pageKey = params.key ?: FAVORITE_SHOW_STARTING_KEY
            val response = favoriteService.requestFavoriteShows(
                memberId = memberId
            ).favoriteShows.map { it.toModel() }

            return LoadResult.Page(
                response,
                prevKey = null,
                nextKey = if (response.isEmpty()) null else pageKey + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}
