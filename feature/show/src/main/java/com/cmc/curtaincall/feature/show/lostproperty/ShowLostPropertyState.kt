package com.cmc.curtaincall.feature.show.lostproperty

import com.cmc.curtaincall.common.designsystem.component.show.lostproperty.LostPropertyType
import com.cmc.curtaincall.core.base.BaseState
import com.cmc.curtaincall.domain.model.lostproperty.LostPropertyDetailModel

data class ShowLostPropertyState(
    val isActiveSearch: Boolean = false,
    val isDoneSearch: Boolean = false,
    val queryString: String = "",
    val lostDate: String? = null,
    val lostItemType: LostPropertyType? = null,
    val lostDetailItem: LostPropertyDetailModel = LostPropertyDetailModel()
) : BaseState
