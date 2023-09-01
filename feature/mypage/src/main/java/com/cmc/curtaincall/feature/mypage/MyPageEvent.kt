package com.cmc.curtaincall.feature.mypage

import com.cmc.curtaincall.core.base.BaseEvent
import com.cmc.curtaincall.domain.model.home.MemberInfoModel

sealed class MyPageEvent : BaseEvent {
    data class LoadMemberInfo(
        val memberInfoModel: MemberInfoModel
    ) : MyPageEvent()
}
