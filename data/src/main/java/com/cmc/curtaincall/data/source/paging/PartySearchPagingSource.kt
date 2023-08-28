package com.cmc.curtaincall.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cmc.curtaincall.core.network.service.party.PartyService
import com.cmc.curtaincall.domain.model.party.PartyModel
import javax.inject.Inject

private const val PARTY_SEARCH_STARTING_KEY = 0
const val PARTY_SEARCH_PAGE_SIZE = 20

class PartySearchPagingSource @Inject constructor(
    private val partyService: PartyService,
    private val category: String,
    private val keyword: String
) : PagingSource<Int, PartyModel>() {
    override fun getRefreshKey(state: PagingState<Int, PartyModel>): Int? {
        return state.anchorPosition?.let { position ->
            val anchorPage = state.closestPageToPosition(position)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PartyModel> {
        try {
            val pageKey = params.key ?: PARTY_SEARCH_STARTING_KEY
            val response = partyService.searchPartyList(
                page = pageKey,
                size = PARTY_SEARCH_PAGE_SIZE,
                category = category,
                keyword = keyword
            ).parties.map { it.toModel() }

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
