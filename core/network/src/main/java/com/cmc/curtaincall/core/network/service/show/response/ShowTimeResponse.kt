package com.cmc.curtaincall.core.network.service.show.response

import com.cmc.curtaincall.domain.model.show.ShowTimeModel

data class ShowTimeResponse(
    val dayOfWeek: String,
    val time: String
) {
    fun toModel() = ShowTimeModel(
        dayOfWeek = dayOfWeek,
        time = time
    )
}