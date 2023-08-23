package com.cmc.curtaincall.feature.performance.lostitem

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.items.EmptyItem
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Black_Coral
import com.cmc.curtaincall.common.design.theme.Bright_Gray
import com.cmc.curtaincall.common.design.theme.Chinese_Black
import com.cmc.curtaincall.common.design.theme.Me_Pink
import com.cmc.curtaincall.common.design.theme.Roman_Silver
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo

@Composable
internal fun PerformanceLostItemTabScreen(
    modifier: Modifier = Modifier,
    onNavigateLostItem: () -> Unit
) {
    Column(modifier.padding(horizontal = 20.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(R.string.performance_find_lost_item),
                color = Chinese_Black,
                fontSize = 17.dp.toSp(),
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo
            )
            Spacer(Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .border(BorderStroke(1.dp, Me_Pink), RoundedCornerShape(14.dp))
                    .padding(vertical = 6.dp, horizontal = 10.dp)
                    .clickable { onNavigateLostItem() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.performance_detail_show_all_review),
                    color = Me_Pink,
                    fontSize = 13.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
            }
        }
        Column(
            modifier = Modifier
                .padding(top = 14.dp)
                .heightIn(min = 317.dp)
        ) {
            EmptyItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 70.dp),
                alert = stringResource(R.string.performance_lostitem_empty)
            )
//            LostItem(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 12.dp),
//                painter = painterResource(R.drawable.img_default_performance),
//                title = "닥스지갑",
//                date = "2023.6.15",
//                getLocation = "LG아트센터 서울",
//                storeLocation = "LG아트센터 서울"
//            )
//            LostItem(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 12.dp),
//                painter = painterResource(R.drawable.img_default_performance),
//                title = "닥스지갑",
//                date = "2023.6.15",
//                getLocation = "LG아트센터 서울",
//                storeLocation = "LG아트센터 서울"
//            )
//            LostItem(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 12.dp),
//                painter = painterResource(R.drawable.img_default_performance),
//                title = "닥스지갑",
//                date = "2023.6.15",
//                getLocation = "LG아트센터 서울",
//                storeLocation = "LG아트센터 서울"
//            )
        }
    }
}

@Composable
private fun PerformanceLostItem(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .border(BorderStroke(1.dp, Bright_Gray), RoundedCornerShape(10.dp))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.img_default_performance),
            contentDescription = null,
            modifier = Modifier.size(63.dp)
        )
        Column(Modifier.padding(start = 12.dp)) {
            Row {
                Text(
                    text = "닥스 지갑",
                    color = Chinese_Black,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = "2023.6.15",
                    color = Roman_Silver,
                    fontSize = 10.dp.toSp(),
                    fontWeight = FontWeight.Normal,
                    fontFamily = spoqahansanseeo
                )
            }
            Spacer(Modifier.weight(1f))
            Text(
                text = "습득 장소 | LG아트센터 서울\n보관 장소 | LG아트센터 서울",
                color = Black_Coral,
                fontSize = 12.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo,
                lineHeight = 16.dp.toSp()
            )
        }
    }
}
