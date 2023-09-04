package com.cmc.curtaincall.feature.mypage.main

import com.cmc.curtaincall.core.base.BaseEvent
import com.cmc.curtaincall.domain.model.member.MemberInfoModel

sealed class MyPageEvent : BaseEvent {
    data class LoadMemberInfo(
        val memberInfoModel: MemberInfoModel
    ) : MyPageEvent()
}
