package com.cmc.curtaincall.feature.performance.search

import com.cmc.curtaincall.core.base.BaseEvent
import com.cmc.curtaincall.domain.enum.ShowGenreType
import com.cmc.curtaincall.domain.enum.ShowSortType

sealed class ShowSearchEvent : BaseEvent {
    object ActivateSearch : ShowSearchEvent()
    object DeActivateSearch : ShowSearchEvent()
    object DoneSearch : ShowSearchEvent()
    object Searching : ShowSearchEvent()
    data class SetQueryString(val query: String) : ShowSearchEvent()
    data class ChangeGenre(val genre: ShowGenreType) : ShowSearchEvent()
    data class ChangeSort(val sortType: ShowSortType) : ShowSearchEvent()
//
//    data class LoadPlayItems(
//        val playItems: Flow<PagingData<ShowInfoModel>>
//    ) : ShowSearchEvent()
//
//    data class LoadMusicalItems(
//        val musicalItems: Flow<PagingData<ShowInfoModel>>
//    ) : ShowSearchEvent()
}
