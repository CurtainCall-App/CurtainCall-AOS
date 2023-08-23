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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.basic.SearchAppBar
import com.cmc.curtaincall.common.design.component.basic.SearchTopAppBarWithBack
import com.cmc.curtaincall.common.design.component.content.card.PartyMemberContentCard
import com.cmc.curtaincall.common.design.component.content.card.PartyType
import com.cmc.curtaincall.common.design.component.items.EmptyItem
import com.cmc.curtaincall.common.design.theme.*
import com.cmc.curtaincall.common.utility.extensions.toChangeFullDate
import com.cmc.curtaincall.common.utility.extensions.toDateWithDay
import com.cmc.curtaincall.common.utility.extensions.toTime
import com.cmc.curtaincall.feature.partymember.ui.PartyMemberViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PartyMemberListScreen(
    partyMemberViewModel: PartyMemberViewModel = hiltViewModel(),
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
                partyMemberViewModel = partyMemberViewModel,
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
    partyMemberViewModel: PartyMemberViewModel,
    partyType: PartyType,
    modifier: Modifier = Modifier,
    onNavigateDetail: (PartyType) -> Unit
) {
    val pagingItems = when (partyType) {
        PartyType.PERFORMANCE -> {
            partyMemberViewModel.watchingItems.collectAsLazyPagingItems()
        }

        PartyType.MEAL -> {
            partyMemberViewModel.foodCafeItems.collectAsLazyPagingItems()
        }

        PartyType.ETC -> {
            partyMemberViewModel.etcItems.collectAsLazyPagingItems()
        }
    }

    Column(modifier = modifier.background(Cultured)) {
        if (pagingItems.itemCount == 0) {
            EmptyItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 252.dp),
                alert = stringResource(R.string.partymember_empty_text)
            )
        } else {
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
                items(pagingItems) { partyModel ->
                    partyModel?.let { partyModel ->
                        PartyMemberContentCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(bottom = 20.dp),
                            partyType = partyType,
                            title = partyModel.showName,
                            nickname = partyModel.creatorNickname,
                            createAtDate = partyModel.createdAt.toChangeFullDate(),
                            createAtTime = partyModel.createdAt.toTime(),
                            numberOfMember = partyModel.curMemberNum,
                            numberOfTotal = partyModel.maxMemberNum,
                            description = partyModel.title,
                            posterUrl = partyModel.showPoster,
                            date = partyModel.showAt.toDateWithDay(),
                            time = partyModel.showAt.toTime(),
                            location = partyModel.facilityName,
                            onNavigateDetail = onNavigateDetail
                        )
                    }
                }
            }
        }
    }
}
