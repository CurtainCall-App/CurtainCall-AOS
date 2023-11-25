package com.cmc.curtaincall.feature.performance.lostitem

import androidx.paging.PagingData
import com.cmc.curtaincall.common.design.component.enum.LostPropertyType
import com.cmc.curtaincall.core.base.BaseState
import com.cmc.curtaincall.domain.model.lostItem.LostItemDetailModel
import com.cmc.curtaincall.domain.model.lostItem.LostItemModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class PerformanceLostItemUiState(
    val isActiveSearch: Boolean = false,
    val isDoneSearch: Boolean = false,
    val queryString: String = "",
    val lostDate: String? = null,
    val lostItemType: LostPropertyType? = null,
    val lostItemSearchItems: Flow<PagingData<LostItemModel>> = flowOf(),
    val lostDetailItem: LostItemDetailModel = LostItemDetailModel()
) : BaseState
