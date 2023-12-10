package com.cmc.curtaincall.feature.performance.search

import androidx.paging.PagingData
import com.cmc.curtaincall.common.designsystem.component.custom.SortType
import com.cmc.curtaincall.core.base.BaseState
import com.cmc.curtaincall.domain.model.show.ShowInfoModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class PerformanceSearchUiState(
    val lastIndex: Int = 0,
    val genre: String = "PLAY",
    val isActiveSearch: Boolean = false,
    val isDoneSearch: Boolean = false,
    val queryString: String = "",
    val sortType: SortType = SortType.STAR,
    val playItems: Flow<PagingData<ShowInfoModel>> = flowOf(),
    val musicalItems: Flow<PagingData<ShowInfoModel>> = flowOf(),
    val showSearchItems: Flow<PagingData<ShowInfoModel>> = flowOf()
) : BaseState
