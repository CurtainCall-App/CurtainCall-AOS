package com.cmc.curtaincall.feature.performance.detail

import com.cmc.curtaincall.core.base.BaseState
import com.cmc.curtaincall.domain.model.show.FacilityDetailModel
import com.cmc.curtaincall.domain.model.show.ShowDetailModel

data class PerformanceDetailState(
    val showDetailModel: ShowDetailModel = ShowDetailModel(),
    val facilityDetailModel: FacilityDetailModel = FacilityDetailModel()
) : BaseState
