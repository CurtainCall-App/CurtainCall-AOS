package com.cmc.curtaincall.design.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.component.basic.SearchTopAppBarWithBack
import com.cmc.curtaincall.common.design.component.basic.TopAppBarOnlySearch
import com.cmc.curtaincall.common.design.component.basic.TopAppBarOnlySetting
import com.cmc.curtaincall.common.design.component.basic.TopAppBarWithBack
import com.cmc.curtaincall.common.design.component.basic.TopAppBarWithClose
import com.cmc.curtaincall.common.design.component.basic.TopAppBarWithDelete
import com.cmc.curtaincall.common.design.component.basic.TopAppBarWithReportAction
import com.cmc.curtaincall.common.design.theme.Black_Coral
import com.cmc.curtaincall.common.design.theme.Cultured
import com.cmc.curtaincall.common.design.theme.Nero
import com.cmc.curtaincall.common.design.theme.Roman_Silver
import com.cmc.curtaincall.common.design.theme.White
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
internal fun AppBarScreen(
    onBack: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Cultured)
    Scaffold(
        topBar = {
            TopAppBarWithBack(
                title = "AppBar",
                containerColor = Cultured,
                contentColor = Nero,
                onClick = onBack
            )
        }
    ) { paddingValues ->
        AppBarContent(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Cultured)
        )
    }
}

@Composable
private fun AppBarContent(
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Column(
            modifier = Modifier
                .padding(top = 15.dp)
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Card(
                modifier = Modifier.padding(bottom = 10.dp),
                shape = RoundedCornerShape(10.dp),
                backgroundColor = White
            ) {
                Column {
                    TopAppBarOnlySetting(
                        containerColor = Cultured,
                        contentColor = Roman_Silver,
                        onClick = {}
                    )
                }
            }
            Card(
                modifier = Modifier
                    .padding(top = 15.dp)
                    .padding(bottom = 10.dp),
                shape = RoundedCornerShape(10.dp),
                backgroundColor = White
            ) {
                Column {
                    TopAppBarOnlySearch(
                        containerColor = Cultured,
                        contentColor = Nero,
                        onClick = {}
                    )
                }
            }
            Card(
                modifier = Modifier
                    .padding(top = 15.dp)
                    .padding(bottom = 10.dp),
                shape = RoundedCornerShape(10.dp),
                backgroundColor = White
            ) {
                Column {
                    TopAppBarWithBack(
                        title = "공연 관람",
                        containerColor = Cultured,
                        contentColor = Nero,
                        onClick = {}
                    )
                }
            }
            Card(
                modifier = Modifier
                    .padding(top = 15.dp)
                    .padding(bottom = 10.dp),
                shape = RoundedCornerShape(10.dp),
                backgroundColor = White
            ) {
                Column {
                    SearchTopAppBarWithBack(
                        title = "공연 관람",
                        containerColor = Cultured,
                        contentColor = Nero,
                        tint = Roman_Silver
                    )
                }
            }
            Card(
                modifier = Modifier
                    .padding(top = 15.dp)
                    .padding(bottom = 10.dp),
                shape = RoundedCornerShape(10.dp),
                backgroundColor = Cultured
            ) {
                Column {
                    TopAppBarWithReportAction(
                        title = "기타",
                        containerColor = Cultured,
                        contentColor = Nero
                    )
                }
            }
            Card(
                modifier = Modifier
                    .padding(top = 15.dp)
                    .padding(bottom = 10.dp),
                shape = RoundedCornerShape(10.dp),
                backgroundColor = White
            ) {
                Column {
                    TopAppBarWithDelete(
                        title = "기타",
                        containerColor = Cultured,
                        contentColor = Nero,
                        deleteColor = Black_Coral
                    )
                }
            }
            Card(
                modifier = Modifier
                    .padding(top = 15.dp)
                    .padding(bottom = 10.dp),
                shape = RoundedCornerShape(10.dp),
                backgroundColor = White
            ) {
                Column {
                    TopAppBarWithClose(
                        title = "분실물 게시물 수정",
                        containerColor = Cultured,
                        contentColor = Nero,
                        onClose = {}
                    )
                }
            }
        }
    }
}
