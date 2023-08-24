package com.cmc.curtaincall.feature.partymember.ui.create

import com.cmc.curtaincall.core.base.BaseState
import com.cmc.curtaincall.domain.model.show.ShowDetailModel

data class PartyMemberCreateState(
    val showDetailModel: ShowDetailModel = ShowDetailModel(),
    val showId: String = "",
    val showAt: String = "",
    val title: String = "",
    val content: String = "",
    val maxMemberNum: Int = 0,
    val category: String = ""
) : BaseState
