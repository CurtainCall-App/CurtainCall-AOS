package com.cmc.curtaincall.feature.performance

import androidx.paging.PagingData
import com.cmc.curtaincall.core.base.BaseEvent
import com.cmc.curtaincall.domain.model.show.ShowInfoModel
import kotlinx.coroutines.flow.Flow

sealed class PerformanceEvent : BaseEvent {
    data class ChangeLastIndex(
        val lastIndex: Int
    ) : PerformanceEvent()

    data class ChangeGenre(
        val genre: String
    ) : PerformanceEvent()

    data class ChangeActiveSearch(
        val isActiveSearch: Boolean
    ) : PerformanceEvent()

    data class ChangeDoneSearch(
        val isDoneSearch: Boolean
    ) : PerformanceEvent()

    data class SetQueryString(
        val queryString: String
    ) : PerformanceEvent()

    data class SearchShowList(
        val showSearchItems: Flow<PagingData<ShowInfoModel>>
    ) : PerformanceEvent()

    data class LoadPlayItems(
        val playItems: Flow<PagingData<ShowInfoModel>>
    ) : PerformanceEvent()

    data class LoadMusicalItems(
        val musicalItems: Flow<PagingData<ShowInfoModel>>
    ) : PerformanceEvent()
}
