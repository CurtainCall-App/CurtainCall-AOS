package com.cmc.curtaincall.feature.livetalk.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.basic.SearchAppBar
import com.cmc.curtaincall.common.design.component.basic.TopAppBarOnlySearch
import com.cmc.curtaincall.common.design.theme.Cetacean_Blue
import com.cmc.curtaincall.common.design.theme.White
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun LiveTalkScreen() {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Cetacean_Blue)

    var isActiveSearchState by remember { mutableStateOf(false) }
    var queryState by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            if (isActiveSearchState) {
                SearchAppBar(
                    value = queryState,
                    onValueChange = { queryState = it },
                    containerColor = Cetacean_Blue,
                    contentColor = White,
                    placeholder = stringResource(R.string.search_performance_title),
                    onClick = { isActiveSearchState = false }
                )
            } else {
                TopAppBarOnlySearch(
                    containerColor = Cetacean_Blue,
                    contentColor = White,
                    onClick = { isActiveSearchState = true }
                )
            }
        }
    ) { paddingValues ->
        if (isActiveSearchState) {
            LiveTalkSearchContent(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(Cetacean_Blue)
            )
        } else {
            LiveTalkContent(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(Cetacean_Blue)
            )
        }
    }
}

@Composable
private fun LiveTalkContent(
    modifier: Modifier = Modifier
) {
    Column(modifier) {
    }
}

// @Composable
// private fun LiveTalkContent(
//    modifier: Modifier = Modifier
// ) {
//    var liveTalkState by remember { mutableStateOf(LiveTalkState.Proceed) }
//    Column(modifier) {
//        Column(Modifier.padding(horizontal = 20.dp)) {
//            Text(
//                text = stringResource(R.string.livetalk_appbar_title),
//                modifier = Modifier.padding(top = 6.dp),
//                color = White,
//                fontSize = 24.dp.toSp(),
//                fontWeight = FontWeight.Bold,
//                fontFamily = spoqahansanseeo
//            )
//            Row(Modifier.padding(top = 12.dp)) {
//                CurtainCallRoundedText(
//                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
//                    text = LiveTalkState.Proceed.value,
//                    containerColor = if (liveTalkState == LiveTalkState.Proceed) Me_Pink else White.copy(0.3f),
//                    contentColor = Cetacean_Blue,
//                    fontSize = 16.dp.toSp(),
//                    radiusSize = 20.dp,
//                    onClick = { liveTalkState = LiveTalkState.Proceed }
//                )
//                Spacer(modifier = Modifier.size(8.dp))
//                CurtainCallRoundedText(
//                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
//                    text = LiveTalkState.Finish.value,
//                    containerColor = if (liveTalkState == LiveTalkState.Finish) Me_Pink else White.copy(0.3f),
//                    contentColor = Cetacean_Blue,
//                    fontSize = 16.dp.toSp(),
//                    radiusSize = 20.dp,
//                    onClick = { liveTalkState = LiveTalkState.Finish }
//                )
//            }
//            Column(Modifier.fillMaxSize()) {
//                Spacer(Modifier.weight(151f))
//                EmptyLiveTalk(
//                    modifier = Modifier.fillMaxWidth(),
//                    alert = stringResource(R.string.livetalk_empty_contents)
//                )
//                Spacer(Modifier.weight(241f))
//            }
//        }
//    }
// }
//
@Composable
private fun LiveTalkSearchContent(
    modifier: Modifier = Modifier
) {
    Column(modifier) {
    }
}
