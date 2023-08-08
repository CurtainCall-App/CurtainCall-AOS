package com.cmc.curtaincall.common.design.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Cadet_Grey
import com.cmc.curtaincall.common.design.theme.Charcoal
import com.cmc.curtaincall.common.design.theme.Chinese_Black
import com.cmc.curtaincall.common.design.theme.Coal
import com.cmc.curtaincall.common.design.theme.Red
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo

enum class CardType {
    TOP10, OPEN_SOON, CHEAP
}

@Composable
fun PerformanceCard(
    cardType: CardType,
    name: String,
    image: Painter,
    rate: Float,
    numberOfRate: Int,
    meta: String = ""
) {
    Card(
        modifier = Modifier
            .padding(end = 12.dp)
            .size(120.dp, 218.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            contentColor = White,
            containerColor = White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .size(120.dp, 160.dp)
                .background(Red)
                .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
        ) {
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            if ((cardType == CardType.TOP10 || cardType == CardType.OPEN_SOON) && meta.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .background(Coal.copy(0.7f), RoundedCornerShape(topStart = 10.dp, bottomEnd = 10.dp))
                        .padding(horizontal = 9.dp, vertical = 7.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = meta,
                        color = White,
                        fontSize = 18.dp.toSp(),
                        fontWeight = FontWeight.Bold,
                        fontFamily = spoqahansanseeo
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = name,
                color = Chinese_Black,
                fontSize = 15.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                modifier = Modifier.padding(top = 3.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_star),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = Color.Unspecified
                )
                Text(
                    text = String.format("%.1f", rate),
                    modifier = Modifier.padding(start = 3.dp),
                    color = Charcoal,
                    fontSize = 13.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )
                Text(
                    text = String.format("(%d)", numberOfRate),
                    modifier = Modifier.padding(start = 2.dp),
                    color = Cadet_Grey,
                    fontSize = 13.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )
            }
        }
    }
}

@Composable
fun LiveTalkCard(
    name: String,
    image: Painter,
    time: String
) {
    Column(
        modifier = Modifier.padding(end = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(72.dp, 96.dp)
                .clip(RoundedCornerShape(7.dp))
        ) {
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            Box(
                modifier = Modifier
                    .background(Red, RoundedCornerShape(topStart = 7.dp, bottomEnd = 7.dp))
                    .padding(vertical = 4.dp, horizontal = 6.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = time,
                    color = White,
                    fontSize = 12.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
            }
        }
        Text(
            text = name,
            modifier = Modifier.padding(top = 8.dp),
            color = Chinese_Black,
            fontSize = 14.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo
        )
    }
}
