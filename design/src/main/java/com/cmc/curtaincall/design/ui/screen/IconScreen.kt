package com.cmc.curtaincall.design.ui.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.component.basic.TopAppBarWithBack
import com.cmc.curtaincall.common.designsystem.extensions.toSp
import com.cmc.curtaincall.common.designsystem.theme.Cetacean_Blue
import com.cmc.curtaincall.common.designsystem.theme.White
import com.cmc.curtaincall.common.designsystem.theme.spoqahansanseeo

@Composable
internal fun IconScreen(
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarWithBack(
                title = "Icons",
                containerColor = Cetacean_Blue,
                contentColor = White,
                onClick = onBack
            )
        }
    ) { paddingValues ->
        IconContent(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Cetacean_Blue)
        )
    }
}

@Composable
private fun IconContent(
    modifier: Modifier = Modifier
) {
    val icons = listOf(
        R.drawable.ic_home to ("홈" to Color.White),
        R.drawable.ic_show to ("작품" to Color.White),
        R.drawable.ic_partymember to ("파티원" to Color.White),
        R.drawable.ic_my to ("MY" to Color.White),
        R.drawable.ic_check to ("Check" to Color.Unspecified),
        R.drawable.ic_fire to ("인기 작품" to Color.Unspecified),
        R.drawable.ic_open_clock to ("오픈 예정" to Color.Unspecified),
        R.drawable.ic_end_clock to ("마감 임박" to Color.Unspecified),
        R.drawable.ic_chatting to ("라이브톡" to Color.Unspecified),
        R.drawable.ic_bookmark to ("북마크" to Color.Unspecified),
        R.drawable.ic_close to ("Close" to Color.Unspecified),
        R.drawable.ic_pen to ("Pen" to Color.Unspecified),
        R.drawable.ic_bag to ("가방" to Color.Unspecified),
        R.drawable.ic_wallet to ("지갑" to Color.Unspecified),
        R.drawable.ic_money to ("현금" to Color.Unspecified),
        R.drawable.ic_card to ("카드" to Color.Unspecified),
        R.drawable.ic_jewelry to ("귀금속" to Color.Unspecified),
        R.drawable.ic_electronics to ("전자기기" to Color.Unspecified),
        R.drawable.ic_books to ("도서" to Color.Unspecified),
        R.drawable.ic_clothes to ("의류" to Color.Unspecified),
        R.drawable.ic_etc to ("기타" to Color.Unspecified),
        R.drawable.ic_subtitles to ("제목" to Color.Unspecified),
        R.drawable.ic_lost_item to ("분류" to Color.Unspecified),
        R.drawable.ic_location_searching to ("습득장소" to Color.Unspecified),
        R.drawable.ic_my_location to ("세부장소" to Color.Unspecified),
        R.drawable.ic_event_available to ("습득일자" to Color.Unspecified),
        R.drawable.ic_alarm_on to ("습득시간" to Color.Unspecified),
        R.drawable.ic_stars to ("특이사항" to Color.Unspecified),
        R.drawable.ic_dns to ("작품명" to Color.White),
        R.drawable.ic_party_state to ("참여 상태" to Color.White),
        R.drawable.ic_calendar to ("공연 일자" to Color.White),
        R.drawable.ic_clock to ("공연 시간" to Color.White),
        R.drawable.ic_location to ("공연 장소" to Color.White)
    )
    Column(modifier) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(icons) {
                IconItem(
                    modifier = Modifier.height(70.dp),
                    iconRes = it.first,
                    title = it.second.first,
                    tint = it.second.second
                )
            }
        }
    }
}

@Composable
private fun IconItem(
    modifier: Modifier = Modifier,
    @DrawableRes iconRes: Int,
    title: String,
    tint: Color = Color.Unspecified
) {
    Box(
        modifier = modifier.border(1.dp, White, RoundedCornerShape(6.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(iconRes),
                contentDescription = null,
                modifier = Modifier.size(25.dp),
                tint = tint
            )
            Text(
                text = title,
                modifier = Modifier.padding(top = 8.dp),
                color = White,
                fontSize = 13.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            )
        }
    }
}
