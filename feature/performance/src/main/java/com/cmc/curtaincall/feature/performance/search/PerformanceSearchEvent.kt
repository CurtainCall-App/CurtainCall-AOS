package com.cmc.curtaincall.feature.performance.search

import androidx.paging.PagingData
import com.cmc.curtaincall.common.designsystem.component.custom.SortType
import com.cmc.curtaincall.core.base.BaseEvent
import com.cmc.curtaincall.domain.model.show.ShowInfoModel
import kotlinx.coroutines.flow.Flow

sealed class PerformanceSearchEvent : BaseEvent {
    data class ChangeLastIndex(
        val lastIndex: Int
    ) : PerformanceSearchEvent()

    data class ChangeGenre(
        val genre: String
    ) : PerformanceSearchEvent()

    data class ChangeActiveSearch(
        val isActiveSearch: Boolean
    ) : PerformanceSearchEvent()

    data class ChangeDoneSearch(
        val isDoneSearch: Boolean
    ) : PerformanceSearchEvent()

    data class ChangeSort(
        val sortType: SortType
    ) : PerformanceSearchEvent()

    data class SetQueryString(
        val queryString: String
    ) : PerformanceSearchEvent()

    data class SearchShowList(
        val showSearchItems: Flow<PagingData<ShowInfoModel>>
    ) : PerformanceSearchEvent()

    data class LoadPlayItems(
        val playItems: Flow<PagingData<ShowInfoModel>>
    ) : PerformanceSearchEvent()

    data class LoadMusicalItems(
        val musicalItems: Flow<PagingData<ShowInfoModel>>
    ) : PerformanceSearchEvent()
}
