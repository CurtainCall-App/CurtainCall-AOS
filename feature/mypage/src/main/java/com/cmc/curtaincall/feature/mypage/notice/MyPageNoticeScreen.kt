package com.cmc.curtaincall.feature.mypage.notice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.basic.TopAppBarWithBack
import com.cmc.curtaincall.common.design.component.items.NoticeItem
import com.cmc.curtaincall.common.design.theme.Cultured
import com.cmc.curtaincall.common.design.theme.Nero
import com.cmc.curtaincall.common.design.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MyPageNoticeScreen(
    onNavigateNoticeDetail: () -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarWithBack(
                title = stringResource(R.string.mypage_announcement),
                containerColor = White,
                contentColor = Nero,
                onClick = onBack
            )
        }
    ) { paddingValues ->
        MyPageNoticeContent(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(White),
            onNavigateNoticeDetail = onNavigateNoticeDetail
        )
    }
}

@Composable
private fun MyPageNoticeContent(
    modifier: Modifier = Modifier,
    onNavigateNoticeDetail: () -> Unit
) {
    val news = listOf(true, true, false, false)
    Column(modifier) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
        ) {
            itemsIndexed(news) { index, item ->
                NoticeItem(
                    modifier = Modifier.fillMaxWidth(),
                    title = "커튼콜 서비스 개편 안내",
                    date = "2023.6.17",
                    isNew = news[index],
                    notRead = news[index],
                    onClick = onNavigateNoticeDetail
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = if (news[index]) 0.dp else 20.dp)
                        .height(1.dp)
                        .background(Cultured)
                )
            }
        }
    }
}
