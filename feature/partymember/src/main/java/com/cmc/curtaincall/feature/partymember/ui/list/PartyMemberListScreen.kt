package com.cmc.curtaincall.feature.partymember.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.basic.SearchAppBar
import com.cmc.curtaincall.common.design.component.basic.SearchTopAppBarWithBack
import com.cmc.curtaincall.common.design.component.content.card.PartyMemberContentCard
import com.cmc.curtaincall.common.design.component.content.card.PartyType
import com.cmc.curtaincall.common.design.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PartyMemberListScreen(
    partyType: PartyType,
    onNavigateDetail: (PartyType) -> Unit,
    onNavigateCreate: (PartyType) -> Unit,
    onBack: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Cultured)

    var isActiveSearchState by remember { mutableStateOf(false) }
    var queryState by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            if (isActiveSearchState) {
                SearchAppBar(
                    value = queryState,
                    onValueChange = { queryState = it },
                    containerColor = White,
                    contentColor = Nero,
                    placeholder = stringResource(R.string.search_performance_title),
                    onClick = { isActiveSearchState = false }
                )
            } else {
                SearchTopAppBarWithBack(
                    title = stringResource(
                        when (partyType) {
                            PartyType.PERFORMANCE -> R.string.partymember_performance_title
                            PartyType.MEAL -> R.string.partymember_restaurant_title
                            PartyType.ETC -> R.string.partymember_etc_title
                        }
                    ),
                    containerColor = Cultured,
                    contentColor = Nero,
                    tint = Roman_Silver,
                    onBack = onBack,
                    onClick = { isActiveSearchState = true }
                )
            }
        },
        floatingActionButton = {
            IconButton(
                onClick = { onNavigateCreate(partyType) },
                modifier = Modifier.size(85.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_fab_write),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
        }
    ) { paddingValues ->
        if (isActiveSearchState) {
            // TODO
        } else {
            PartyMemberListContent(
                partyType = partyType,
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(Cultured),
                onNavigateDetail = onNavigateDetail
            )
        }
    }
}

@Composable
private fun PartyMemberListContent(
    partyType: PartyType,
    modifier: Modifier = Modifier,
    onNavigateDetail: (PartyType) -> Unit
) {
    Column(modifier = modifier.background(Cultured)) {
//        EmptyItem(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 252.dp),
//            alert = stringResource(R.string.partymember_empty_text)
//        )
        LazyColumn(
            modifier = Modifier
                .padding(top = 30.dp)
                .padding(horizontal = 20.dp)
        ) {
            item {
                Text(
                    text = stringResource(R.string.partymember_list_description),
                    modifier = Modifier.padding(bottom = 12.dp),
                    color = Black_Coral,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
            }

            items(10) {
                PartyMemberContentCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(bottom = 20.dp),
                    partyType = partyType,
                    title = "비스티",
                    nickname = "고라파덕",
                    createAtDate = "2023.06.07",
                    createAtTime = "11:51",
                    numberOfMember = 1,
                    numberOfTotal = 5,
                    description = "비스티 이번주 토욜 저녁 공연 같이 봐요~",
                    poster = painterResource(R.drawable.img_poster),
                    date = "2023.6.24(토)",
                    time = "19:30",
                    location = "링크아트센터",
                    onNavigateDetail = onNavigateDetail
                )
            }
        }
    }
}
