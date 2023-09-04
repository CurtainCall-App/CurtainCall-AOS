package com.cmc.curtaincall.feature.mypage.notice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.basic.TopAppBarWithBack
import com.cmc.curtaincall.common.design.component.items.NoticeItem
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Arsenic
import com.cmc.curtaincall.common.design.theme.Cultured
import com.cmc.curtaincall.common.design.theme.Nero
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo
import com.cmc.curtaincall.common.utility.extensions.toChangeFullDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MyPageNoticeDetailScreen(
    myPageNoticeViewModel: MyPageNoticeViewModel,
    noticeId: Int,
    onBack: () -> Unit
) {
    LaunchedEffect(Unit) {
        myPageNoticeViewModel.requestNoticeDetail(noticeId)
    }

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
        MyPageNoticeDetailContent(
            myPageNoticeViewModel = myPageNoticeViewModel,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(White)
        )
    }
}

@Composable
private fun MyPageNoticeDetailContent(
    myPageNoticeViewModel: MyPageNoticeViewModel,
    modifier: Modifier = Modifier
) {
    val myPageNoticeDetailUiState by myPageNoticeViewModel.noticeDetailState.collectAsStateWithLifecycle()
    Column(modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
        ) {
            NoticeItem(
                modifier = Modifier.fillMaxWidth(),
                title = myPageNoticeDetailUiState.title,
                date = myPageNoticeDetailUiState.createdAt.toChangeFullDate()
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(1.dp)
                    .background(Cultured)
            )
            Text(
                text = myPageNoticeDetailUiState.content,
                modifier = Modifier
                    .padding(top = 30.dp)
                    .padding(horizontal = 20.dp),
                color = Arsenic,
                fontSize = 14.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo,
                lineHeight = 22.dp.toSp()
            )
        }
    }
}
