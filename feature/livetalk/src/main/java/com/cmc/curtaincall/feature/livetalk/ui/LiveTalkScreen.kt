package com.cmc.curtaincall.feature.livetalk.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.content.card.LiveTalkCard
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Bright_Gray
import com.cmc.curtaincall.common.design.theme.Cetacean_Blue
import com.cmc.curtaincall.common.design.theme.Cultured
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo
import com.cmc.curtaincall.common.utility.extensions.toTime
import com.cmc.curtaincall.feature.livetalk.LiveTalkViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import io.getstream.chat.android.client.ChatClient

@Composable
fun LiveTalkScreen(
    liveTalkViewModel: LiveTalkViewModel = hiltViewModel(),
    chatClient: ChatClient,
    onNavigateDetail: (String, String, String) -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Cetacean_Blue)

    var isActiveSearchState by remember { mutableStateOf(false) }
    var queryState by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp)
                    .background(Cetacean_Blue)
            )
        }
//        topBar = {
//            if (isActiveSearchState) {
//                SearchAppBar(
//                    value = queryState
//                    onValueChange = { queryState = it },
//                    containerColor = Cetacean_Blue,
//                    contentColor = White,
//                    placeholder = stringResource(R.string.search_performance_title),
//                    onClick = { isActiveSearchState = false }
//                )
//            } else {
//                TopAppBarOnlySearch(
//                    containerColor = Cetacean_Blue,
//                    contentColor = White,
//                    onClick = { isActiveSearchState = true }
//                )
//            }
//        }
    ) { paddingValues ->
//        if (isActiveSearchState) {
//            LiveTalkSearchContent(
//                modifier = Modifier
//                    .padding(paddingValues)
//                    .fillMaxSize()
//                    .background(Cetacean_Blue)
//            )
//        } else {
//
//        }
        LiveTalkContent(
            liveTalkViewModel = liveTalkViewModel,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Cetacean_Blue),
            chatClient = chatClient,
            onNavigateDetail = onNavigateDetail
        )
    }
}

@Composable
private fun LiveTalkContent(
    liveTalkViewModel: LiveTalkViewModel,
    modifier: Modifier = Modifier,
    chatClient: ChatClient,
    onNavigateDetail: (String, String, String) -> Unit
) {
    val liveTalkShows by liveTalkViewModel.liveTalkShows.collectAsStateWithLifecycle()
    Column(modifier) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item(span = { GridItemSpan(2) }) {
                Column {
                    Text(
                        text = stringResource(R.string.livetalk_app_bar_title),
                        color = White,
                        fontSize = 24.dp.toSp(),
                        fontWeight = FontWeight.Bold,
                        fontFamily = spoqahansanseeo
                    )
                    Text(
                        text = stringResource(R.string.livetalk_app_bar_description),
                        modifier = Modifier.padding(top = 2.dp),
                        color = White,
                        fontSize = 14.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo
                    )
                    Spacer(modifier = Modifier.size(30.dp))
                }
            }

            if (liveTalkShows.isEmpty()) {
                item(span = { GridItemSpan(2) }) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 190.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_error_report),
                            contentDescription = null,
                            modifier = Modifier.size(60.dp),
                            tint = Cultured
                        )
                        Text(
                            text = "지금은 진행 중인 라이브톡이 없어요!",
                            modifier = Modifier.padding(top = 12.dp),
                            color = Bright_Gray,
                            fontSize = 15.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo
                        )
                    }
                }
            } else {
                items(liveTalkShows) { liveTalkShow ->
                    LiveTalkCard(
                        modifier = Modifier.width(152.dp),
                        startTime = liveTalkShow.showAt.toTime(),
                        endTime = liveTalkShow.showEndAt.toTime(),
                        posterUrl = liveTalkShow.poster,
                        name = liveTalkShow.name,
                        facilityName = liveTalkShow.facilityName,
                        onClick = {
                            onNavigateDetail(
                                liveTalkShow.id,
                                liveTalkShow.name,
                                liveTalkShow.showAt
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun LiveTalkSearchContent(
    modifier: Modifier = Modifier
) {
    Column(modifier) {
    }
}
