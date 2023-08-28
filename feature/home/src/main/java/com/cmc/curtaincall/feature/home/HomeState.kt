package com.cmc.curtaincall.feature.home

import com.cmc.curtaincall.core.base.BaseState
import com.cmc.curtaincall.domain.model.home.MyParticipationModel
import com.cmc.curtaincall.domain.model.home.MyRecruitmentModel
import com.cmc.curtaincall.domain.model.show.ShowInfoModel
import com.cmc.curtaincall.domain.model.show.ShowRankModel
import com.cmc.curtaincall.domain.model.show.ShowSearchWordModel

data class HomeState(
    val nickname: String = "",
    val myRecruitments: List<MyRecruitmentModel> = listOf(),
    val myParticipations: List<MyParticipationModel> = listOf(),
    val liveTalks: List<Int> = listOf<Int>(),
    val showRanks: List<ShowRankModel> = listOf(),
    val openShowInfos: List<ShowInfoModel> = listOf(),
    val endShowInfos: List<ShowInfoModel> = listOf(),
    val searchWords: List<ShowSearchWordModel> = listOf()
) : BaseState
