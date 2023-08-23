package com.cmc.curtaincall.feature.mypage.party.recruitment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.basic.TopAppBarWithBack
import com.cmc.curtaincall.common.design.component.content.card.PartyMemberContentCard
import com.cmc.curtaincall.common.design.component.content.card.PartyType
import com.cmc.curtaincall.common.design.theme.Bright_Gray
import com.cmc.curtaincall.common.design.theme.Nero
import com.cmc.curtaincall.feature.mypage.party.MyPagePartyMenuTab
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MyPageRecruitmentScreen(
    onNavigatRecruitmentDetail: (PartyType) -> Unit,
    onBack: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Bright_Gray)

    Scaffold(
        topBar = {
            TopAppBarWithBack(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                title = stringResource(R.string.mypage_my_gathering_tab),
                containerColor = Bright_Gray,
                contentColor = Nero,
                onClick = onBack
            )
        }
    ) { paddingValues ->
        MyPageRecruitmentContent(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Bright_Gray),
            onNavigatRecruitmentDetail = onNavigatRecruitmentDetail
        )
    }
}

@Composable
private fun MyPageRecruitmentContent(
    modifier: Modifier = Modifier,
    onNavigatRecruitmentDetail: (PartyType) -> Unit
) {
    var partyTypeState by remember { mutableStateOf(PartyType.PERFORMANCE) }
    Column(modifier) {
        MyPagePartyMenuTab(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 30.dp),
            partyType = partyTypeState,
            onChangePartType = { partyTypeState = it }
        )

        LazyColumn(
            modifier = Modifier
                .padding(top = 11.dp)
                .fillMaxSize()
        ) {
            item(10) {
                PartyMemberContentCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(bottom = 20.dp),
                    partyType = partyTypeState,
                    title = "비스티",
                    nickname = "고라파덕",
                    createAtDate = "2023.06.07",
                    createAtTime = "11:51",
                    numberOfMember = 1,
                    numberOfTotal = 5,
                    description = "비스티 이번주 토욜 저녁 공연 같이 봐요~",
                    posterUrl = "",
                    date = "2023.6.24(토)",
                    time = "19:30",
                    location = "링크아트센터",
                    hasLiveTalk = true,
                    onNavigateDetail = { onNavigatRecruitmentDetail(partyTypeState) }
                )
            }
        }
    }
}
