package com.cmc.curtaincall.feature.mypage.announcemnet

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.component.TopAppBarWithBack
import com.cmc.curtaincall.common.design.theme.Nero
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Bright_Gray
import com.cmc.curtaincall.common.design.theme.Charcoal
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MyPageNoticeDetailScreen(
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarWithBack(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                title = stringResource(R.string.mypage_announcement),
                containerColor = White,
                contentColor = Nero,
                onClick = onBack
            )
        }
    ) { paddingValues ->
        MyPageNoticeDetailContent(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(White)
        )
    }
}

@Composable
private fun MyPageNoticeDetailContent(
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .padding(horizontal = 20.dp)
        ) {
            NoticeItem(
                modifier = Modifier.fillMaxWidth(),
                title = "커튼콜 서비스 개편 안내",
                date = "2023.6.17",
                isNew = true
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Bright_Gray)
            )
            Text(
                text = "안녕하세요, 고객님\n" +
                    "연극과 뮤지컬 이ㅏㄹ니ㅏㅇㄹ하는 커튼콜입니다.\n" +
                    "문의해 주신 내용에 대해서 현재니아ㅓ리나\n" +
                    "아울러, 니ㅏ어리ㅏ넝ㄹㄴㅇㄹ 안내해 드릴 수 있도록\n" +
                    "하겠습니다.\n" +
                    "추후 이용 중 다른 문의 사항이 발생한다면 고객센터로 말씀 주시기 바랍니다.\n" +
                    "감사합니다.\n" +
                    "커튼콜팀 드림",
                modifier = Modifier.padding(top = 40.dp),
                color = Charcoal,
                fontSize = 14.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo,
                lineHeight = 22.dp.toSp()
            )
        }
    }
}
