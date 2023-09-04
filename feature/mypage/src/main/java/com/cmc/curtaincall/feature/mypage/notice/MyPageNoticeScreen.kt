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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.basic.TopAppBarWithBack
import com.cmc.curtaincall.common.design.component.items.NoticeItem
import com.cmc.curtaincall.common.design.theme.Cultured
import com.cmc.curtaincall.common.design.theme.Nero
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.utility.extensions.toChangeFullDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MyPageNoticeScreen(
    myPageNoticeViewModel: MyPageNoticeViewModel = hiltViewModel(),
    onNavigateNoticeDetail: (Int) -> Unit,
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
            myPageNoticeViewModel = myPageNoticeViewModel,
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
    myPageNoticeViewModel: MyPageNoticeViewModel,
    modifier: Modifier = Modifier,
    onNavigateNoticeDetail: (Int) -> Unit
) {
    val noticeItems = myPageNoticeViewModel.noticeItems.collectAsLazyPagingItems()
    Column(modifier) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
        ) {
            itemsIndexed(noticeItems) { index, noticeItem ->
                noticeItem?.let { notice ->
                    NoticeItem(
                        modifier = Modifier.fillMaxWidth(),
                        title = notice.title,
                        date = notice.createdAt.toChangeFullDate(),
                        onClick = { onNavigateNoticeDetail(notice.id) }
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .height(1.dp)
                            .background(Cultured)
                    )
                }
            }
        }
    }
}
