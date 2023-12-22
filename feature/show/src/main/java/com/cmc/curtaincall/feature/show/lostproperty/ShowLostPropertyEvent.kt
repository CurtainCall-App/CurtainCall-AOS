package com.cmc.curtaincall.feature.show.lostproperty

import com.cmc.curtaincall.common.designsystem.component.show.lostproperty.LostPropertyType
import com.cmc.curtaincall.core.base.BaseEvent
import com.cmc.curtaincall.domain.model.lostproperty.LostPropertyDetailModel

sealed class ShowLostPropertyEvent : BaseEvent {

    data class ChangeActiveSearch(
        val isActiveSearch: Boolean
    ) : ShowLostPropertyEvent()

    data class ChangeDoneSearch(
        val isDoneSearch: Boolean
    ) : ShowLostPropertyEvent()

    data class SelectDate(
        val date: String
    ) : ShowLostPropertyEvent()

    data class SelectPropertyType(
        val lostItemType: LostPropertyType
    ) : ShowLostPropertyEvent()

    data class SetQueryString(
        val queryString: String
    ) : ShowLostPropertyEvent()

    data class LoadLostDetailProperty(
        val lostDetailItem: LostPropertyDetailModel
    ) : ShowLostPropertyEvent()
}
