package com.cmc.curtaincall.feature.performance.lostitem

import androidx.paging.PagingData
import com.cmc.curtaincall.common.design.component.enum.LostPropertyType
import com.cmc.curtaincall.core.base.BaseEvent
import com.cmc.curtaincall.domain.model.lostItem.LostItemDetailModel
import com.cmc.curtaincall.domain.model.lostItem.LostItemModel
import kotlinx.coroutines.flow.Flow

sealed class PerformanceLostItemEvent : BaseEvent {

    data class ChangeActiveSearch(
        val isActiveSearch: Boolean
    ) : PerformanceLostItemEvent()

    data class ChangeDoneSearch(
        val isDoneSearch: Boolean
    ) : PerformanceLostItemEvent()

    data class SelectDate(
        val date: String
    ) : PerformanceLostItemEvent()

    data class SelectItemType(
        val lostItemType: LostPropertyType
    ) : PerformanceLostItemEvent()

    data class SetQueryString(
        val queryString: String
    ) : PerformanceLostItemEvent()

    data class SearchLostItemList(
        val lostItems: Flow<PagingData<LostItemModel>>
    ) : PerformanceLostItemEvent()

    data class LoadLostDetailItem(
        val lostDetailItem: LostItemDetailModel
    ) : PerformanceLostItemEvent()
}
