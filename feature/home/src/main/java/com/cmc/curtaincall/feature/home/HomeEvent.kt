package com.cmc.curtaincall.feature.home

import com.cmc.curtaincall.core.base.BaseEvent
import com.cmc.curtaincall.domain.model.home.MyParticipationModel
import com.cmc.curtaincall.domain.model.home.MyRecruitmentModel
import com.cmc.curtaincall.domain.model.show.ShowInfoModel
import com.cmc.curtaincall.domain.model.show.ShowRankModel

sealed class HomeEvent : BaseEvent {
    data class GetNickname(
        val nickname: String
    ) : HomeEvent()

    data class RequestMyRecruitment(
        val myRecruitments: List<MyRecruitmentModel>
    ) : HomeEvent()

    data class RequestMyParticipations(
        val myParticipations: List<MyParticipationModel>
    ) : HomeEvent()

    data class RequestPopularShowList(
        val showRanks: List<ShowRankModel>
    ) : HomeEvent()

    data class RequestOpenShowList(
        val openShowInfos: List<ShowInfoModel>
    ) : HomeEvent()
}
