package com.cmc.curtaincall.feature.mypage.main

import com.cmc.curtaincall.core.base.BaseState
import com.cmc.curtaincall.domain.model.member.MemberInfoModel

data class MyPageUiState(
    val memberInfoModel: MemberInfoModel = MemberInfoModel()
) : BaseState
