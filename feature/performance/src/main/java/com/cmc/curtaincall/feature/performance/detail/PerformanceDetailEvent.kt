package com.cmc.curtaincall.feature.performance.detail

import com.cmc.curtaincall.core.base.BaseEvent
import com.cmc.curtaincall.domain.model.show.FacilityDetailModel
import com.cmc.curtaincall.domain.model.show.ShowDetailModel

sealed class PerformanceDetailEvent : BaseEvent {

    data class RequestShowDetail(
        val showDetailModel: ShowDetailModel
    ) : PerformanceDetailEvent()

    data class RequestFacilityDetail(
        val facilityDetailModel: FacilityDetailModel
    ) : PerformanceDetailEvent()
}
