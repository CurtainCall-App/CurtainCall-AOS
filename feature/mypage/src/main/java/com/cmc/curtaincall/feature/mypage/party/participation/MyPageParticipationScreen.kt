package com.cmc.curtaincall.feature.mypage.party.participation

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.basic.TopAppBarWithBack
import com.cmc.curtaincall.common.design.component.content.card.PartyMemberContentCard
import com.cmc.curtaincall.common.design.component.content.card.PartyType
import com.cmc.curtaincall.common.design.component.items.EmptyItem
import com.cmc.curtaincall.common.design.theme.Bright_Gray
import com.cmc.curtaincall.common.design.theme.Me_Pink
import com.cmc.curtaincall.common.design.theme.Nero
import com.cmc.curtaincall.common.utility.extensions.toChangeDate
import com.cmc.curtaincall.common.utility.extensions.toDateWithDay
import com.cmc.curtaincall.common.utility.extensions.toTime
import com.cmc.curtaincall.feature.mypage.MyPageViewModel
import com.cmc.curtaincall.feature.mypage.party.MyPagePartyMenuTab
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPageParticipationScreen(
    myPageViewModel: MyPageViewModel = hiltViewModel(),
    onNavigateParticipationDetail: (PartyType) -> Unit,
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
                title = stringResource(R.string.mypage_my_participation_tab),
                containerColor = Bright_Gray,
                contentColor = Nero,
                onClick = onBack
            )
        }
    ) { paddingValues ->
        MyPageRecruitmentContent(
            myPageViewModel = myPageViewModel,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Bright_Gray),
            onNavigateParticipationDetail = onNavigateParticipationDetail
        )
    }
}

@Composable
private fun MyPageRecruitmentContent(
    myPageViewModel: MyPageViewModel,
    modifier: Modifier = Modifier,
    onNavigateParticipationDetail: (PartyType) -> Unit
) {
    var partyTypeState by remember { mutableStateOf(PartyType.PERFORMANCE) }
    val participationItems = when (partyTypeState) {
        PartyType.PERFORMANCE -> {
            myPageViewModel.watchingParticipationItems.collectAsLazyPagingItems()
        }

        PartyType.MEAL -> {
            myPageViewModel.foodParticipationItems.collectAsLazyPagingItems()
        }

        PartyType.ETC -> {
            myPageViewModel.etcParticipationItems.collectAsLazyPagingItems()
        }
    }
    Column(modifier) {
        MyPagePartyMenuTab(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 30.dp),
            partyType = partyTypeState,
            onChangePartType = { partyTypeState = it }
        )

        if (participationItems.itemCount == 0) {
            EmptyItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 238.dp),
                alert = "아직 참여 중인 파티원이 없어요",
                iconColor = Me_Pink,
                contentColor = Nero
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(top = 11.dp)
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
            ) {
                items(participationItems) { participationModel ->
                    participationModel?.let { participation ->
                        PartyMemberContentCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(bottom = 20.dp),
                            partyType = partyTypeState,
                            title = participation.showName,
                            nickname = participation.creatorNickname,
                            createAtDate = participation.createdAt.toChangeDate(),
                            createAtTime = participation.createdAt.toTime(),
                            numberOfMember = participation.curMemberNum,
                            numberOfTotal = participation.maxMemberNum,
                            description = participation.title,
                            posterUrl = participation.showPoster,
                            date = participation.showAt.toDateWithDay(),
                            time = participation.showAt.toTime(),
                            location = participation.facilityName,
                            hasLiveTalk = false,
                            onClick = { onNavigateParticipationDetail(partyTypeState) }
                        )
                    }
                }
            }
        }
    }
}
