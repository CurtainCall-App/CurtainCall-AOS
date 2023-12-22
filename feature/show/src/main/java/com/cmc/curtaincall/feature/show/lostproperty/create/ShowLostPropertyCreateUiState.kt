package com.cmc.curtaincall.feature.show.lostproperty.create

import com.cmc.curtaincall.common.designsystem.component.show.lostproperty.LostPropertyType
import com.cmc.curtaincall.core.base.BaseState

data class ShowLostPropertyCreateUiState(
    val title: String = "",
    val lostPropertyType: LostPropertyType = LostPropertyType.ETC,
    val foundPlaceDetail: String = "",
    val foundDate: String = "",
    val foundTime: String? = null,
    val particulars: String = "",
    val imageId: Int = Int.MIN_VALUE
) : BaseState
