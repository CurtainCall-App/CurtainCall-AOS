package com.cmc.curtaincall.feature.show.lostproperty.create

import com.cmc.curtaincall.common.designsystem.component.show.lostproperty.LostPropertyType
import com.cmc.curtaincall.core.base.BaseEvent

sealed class ShowLostPropertyCreateEvent : BaseEvent {
    data class SetTitle(val title: String) : ShowLostPropertyCreateEvent()

    data class SetPropertyType(val lostPropertyType: LostPropertyType) : ShowLostPropertyCreateEvent()

    data class SetFoundPlaceDetail(val foundPlaceDetail: String) : ShowLostPropertyCreateEvent()

    data class SetFoundDate(val foundDate: String) : ShowLostPropertyCreateEvent()

    data class SetFoundTime(val foundTime: String) : ShowLostPropertyCreateEvent()

    data class SetParticulars(val particulars: String) : ShowLostPropertyCreateEvent()

    data class SetImageId(val imageId: Int) : ShowLostPropertyCreateEvent()
}
