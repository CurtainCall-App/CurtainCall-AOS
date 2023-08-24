package com.cmc.curtaincall.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cmc.curtaincall.core.network.service.party.PartyService
import com.cmc.curtaincall.core.network.service.party.response.PartyResponse
import javax.inject.Inject

private const val PARTY_STARTING_KEY = 0
const val PARTY_PAGE_SIZE = 20

class PartyPagingSource @Inject constructor(
    private val partyService: PartyService,
    private val category: String
) : PagingSource<Int, PartyResponse>() {
    override fun getRefreshKey(state: PagingState<Int, PartyResponse>): Int? {
        return state.anchorPosition?.let { position ->
            val anchorPage = state.closestPageToPosition(position)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PartyResponse> {
        try {
            val pageKey = params.key ?: PARTY_STARTING_KEY
            val response = partyService.requestPartyList(
                page = pageKey,
                size = PARTY_PAGE_SIZE,
                category = category
            )
            return LoadResult.Page(
                response.parties,
                prevKey = null,
                nextKey = if (response.parties.isEmpty()) null else pageKey + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}
