package com.cmc.curtaincall.feature.home.guide

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.designsystem.extensions.toSp
import com.cmc.curtaincall.common.designsystem.theme.Cetacean_Blue
import com.cmc.curtaincall.common.designsystem.theme.Silver_Sand
import com.cmc.curtaincall.common.designsystem.theme.White
import com.cmc.curtaincall.common.designsystem.theme.spoqahansanseeo

@Composable
internal fun HomeGuideMenuTab(
    modifier: Modifier = Modifier,
    menus: List<Pair<String, @Composable () -> Unit>>
) {
    var clickMenuIndex by remember { mutableStateOf(0) }
    Column(modifier) {
        LazyRow {
            itemsIndexed(menus) { index, item ->
                Box(
                    modifier = Modifier
                        .padding(end = 8.dp, start = if (index == 0) 20.dp else 0.dp)
                        .background(if (clickMenuIndex == index) Cetacean_Blue else White, RoundedCornerShape(20.dp))
                        .padding(vertical = 8.dp, horizontal = 14.dp)
                        .clickable { clickMenuIndex = index },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = item.first,
                        color = if (clickMenuIndex == index) White else Silver_Sand,
                        fontSize = 16.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 26.dp)
                .background(White, RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
        ) {
            menus[clickMenuIndex].second()
        }
    }
}
