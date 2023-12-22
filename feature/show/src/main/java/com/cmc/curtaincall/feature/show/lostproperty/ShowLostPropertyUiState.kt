package com.cmc.curtaincall.feature.show.lostproperty

import androidx.paging.PagingData
import com.cmc.curtaincall.common.designsystem.component.show.lostproperty.LostPropertyType
import com.cmc.curtaincall.core.base.BaseState
import com.cmc.curtaincall.domain.model.lostproperty.LostPropertyDetailModel
import com.cmc.curtaincall.domain.model.lostproperty.LostPropertyModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class ShowLostPropertyUiState(
    val isActiveSearch: Boolean = false,
    val isDoneSearch: Boolean = false,
    val queryString: String = "",
    val lostDate: String? = null,
    val lostItemType: LostPropertyType? = null,
    val lostItemSearchItems: Flow<PagingData<LostPropertyModel>> = flowOf(),
    val lostDetailItem: LostPropertyDetailModel = LostPropertyDetailModel()
) : BaseState
