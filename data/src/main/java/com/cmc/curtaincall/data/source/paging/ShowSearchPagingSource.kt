package com.cmc.curtaincall.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cmc.curtaincall.core.network.service.favorite.FavoriteService
import com.cmc.curtaincall.core.network.service.show.ShowService
import com.cmc.curtaincall.domain.model.show.ShowInfoModel
import javax.inject.Inject

private const val SHOW_SEARCH_STARTING_KEY = 0
const val SHOW_SEARCH_PAGE_SIZE = 10

class ShowSearchPagingSource @Inject constructor(
    private val showService: ShowService,
    private val favoriteService: FavoriteService,
    private val keyword: String
) : PagingSource<Int, ShowInfoModel>() {
    override fun getRefreshKey(state: PagingState<Int, ShowInfoModel>): Int? {
        return state.anchorPosition?.let { position ->
            val anchorPage = state.closestPageToPosition(position)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ShowInfoModel> {
        try {
            val pageKey = params.key ?: SHOW_SEARCH_STARTING_KEY
            val response = showService.searchShowList(
                page = pageKey,
                size = SHOW_SEARCH_PAGE_SIZE,
                keyword = keyword
            )
            val models = response.showInfos.map { response ->
                response.toModel().copy(
                    favorite =
                    favoriteService.checkFavoriteShows(listOf(response.id))
                        .checkFavoriteShows
                        .first()
                        .favorite
                )
            }
            return LoadResult.Page(
                models,
                prevKey = null,
                nextKey = if (models.isEmpty()) null else pageKey + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}
