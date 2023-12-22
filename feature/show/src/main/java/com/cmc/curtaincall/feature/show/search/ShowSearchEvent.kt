package com.cmc.curtaincall.feature.show.search

import com.cmc.curtaincall.core.base.BaseEvent
import com.cmc.curtaincall.domain.type.ShowGenreType
import com.cmc.curtaincall.domain.type.ShowSortType

sealed class ShowSearchEvent : BaseEvent {
    object ActivateSearch : ShowSearchEvent()
    object DeActivateSearch : ShowSearchEvent()
    object DoneSearch : ShowSearchEvent()
    object Searching : ShowSearchEvent()
    data class SetQueryString(val query: String) : ShowSearchEvent()
    data class ChangeGenre(val genre: ShowGenreType) : ShowSearchEvent()
    data class ChangeSort(val sortType: ShowSortType) : ShowSearchEvent()
}
