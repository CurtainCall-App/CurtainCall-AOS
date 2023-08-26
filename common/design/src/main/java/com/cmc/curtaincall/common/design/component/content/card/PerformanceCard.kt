package com.cmc.curtaincall.common.design.component.content.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Arsenic
import com.cmc.curtaincall.common.design.theme.Bright_Gray
import com.cmc.curtaincall.common.design.theme.Cetacean_Blue
import com.cmc.curtaincall.common.design.theme.Chinese_Black
import com.cmc.curtaincall.common.design.theme.Coal
import com.cmc.curtaincall.common.design.theme.Me_Pink
import com.cmc.curtaincall.common.design.theme.Nero
import com.cmc.curtaincall.common.design.theme.Roman_Silver
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo

@Composable
fun PerformanceDetailCard(
    modifier: Modifier = Modifier,
    imageUrl: String? = null,
    painter: Painter,
    title: String,
    rate: Float,
    numberOfTotal: Int,
    period: String,
    runningTime: String,
    date: String,
    location: String,
    onClick: () -> Unit = {},
    isFavorite: Boolean = false,
    onFavorite: () -> Unit = {},
    onDisFavorite: () -> Unit = {}
) {
    var favoriteState by remember { mutableStateOf(isFavorite) }
    Row(modifier.clickable { onClick() }) {
        AsyncImage(
            model = imageUrl,
            error = painter,
            contentDescription = null,
            modifier = Modifier
                .weight(114f)
                .aspectRatio(114 / 153f)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .weight(200f)
                .padding(start = 14.dp)
        ) {
            Row {
                Column(Modifier.weight(1f)) {
                    Text(
                        text = title,
                        color = Chinese_Black,
                        fontSize = 16.dp.toSp(),
                        fontWeight = FontWeight.Bold,
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
                            tint = Me_Pink
                        )
                        Text(
                            text = String.format("%.1f", rate),
                            modifier = Modifier.padding(start = 2.dp),
                            color = Arsenic,
                            fontSize = 14.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo
                        )
                        Text(
                            text = String.format("(%d)", numberOfTotal),
                            modifier = Modifier.padding(start = 2.dp),
                            color = Roman_Silver,
                            fontSize = 14.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo
                        )
                    }
                }
                IconButton(
                    onClick = {
                        favoriteState = favoriteState.not()
                        if (favoriteState) onFavorite() else onDisFavorite()
                    },
                    modifier = Modifier
                        .padding(start = 3.dp)
                        .clip(CircleShape)
                        .size(26.dp),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = if (favoriteState) Cetacean_Blue else Bright_Gray
                    )
                ) {
                    Icon(
                        painter = painterResource(if (favoriteState) R.drawable.ic_bookmark_sel else R.drawable.ic_bookmark),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
            }

            Row(
                modifier = Modifier.padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.performance_period),
                    color = Roman_Silver,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
                Text(
                    text = period,
                    modifier = Modifier.padding(start = 13.dp),
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    lineHeight = 22.sp
                )
            }
            Row(
                modifier = Modifier.padding(top = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.performance_running_time),
                    color = Roman_Silver,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
                Text(
                    text = runningTime,
                    modifier = Modifier.padding(start = 13.dp),
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    lineHeight = 22.sp
                )
            }
            Row(Modifier.padding(top = 4.dp)) {
                Text(
                    text = stringResource(R.string.performance_date),
                    color = Roman_Silver,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
                Column(Modifier.padding(start = 13.dp)) {
                    Text(
                        text = date,
                        color = Nero,
                        fontSize = 14.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo,
                        lineHeight = 22.dp.toSp()
                    )
                }
            }
            Row(
                modifier = Modifier.padding(top = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.performance_location),
                    color = Roman_Silver,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
                Text(
                    text = location,
                    modifier = Modifier.padding(start = 13.dp),
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
            }
        }
    }
}

@Composable
fun PerformanceCard(
    modifier: Modifier = Modifier,
    title: String,
    painter: Painter,
    imageUrl: String? = null,
    rate: Float,
    numberOfTotal: Int,
    isShowMetadata: Boolean = false,
    meta: String = "",
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .aspectRatio(120 / 218f)
            .clickable { onClick() },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = White,
            contentColor = White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(120 / 160f)
                .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
        ) {
            AsyncImage(
                model = imageUrl,
                error = painter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)),
                contentScale = ContentScale.FillBounds
            )
            if (isShowMetadata) {
                Box(
                    modifier = Modifier
                        .background(Coal.copy(0.7f), RoundedCornerShape(topStart = 10.dp, bottomEnd = 10.dp))
                        .padding(horizontal = 10.dp, vertical = 7.dp),
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
                .padding(vertical = 8.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
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
                    tint = Me_Pink
                )
                Text(
                    text = String.format("%.1f", rate),
                    modifier = Modifier.padding(start = 2.dp),
                    color = Arsenic,
                    fontSize = 13.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )
                Text(
                    text = String.format("(%d)", numberOfTotal),
                    modifier = Modifier.padding(start = 2.dp),
                    color = Roman_Silver,
                    fontSize = 13.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )
            }
        }
    }
}

@Composable
fun PerformanceSimpleCard(
    modifier: Modifier,
    imageUrl: String? = null,
    title: String,
    currentIndex: Int,
    selectedIndex: Int,
    onChangeSelect: (Int) -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier.clickable {
                onChangeSelect(if (currentIndex == selectedIndex) -1 else currentIndex)
            },
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(
                width = 4.dp,
                color = if (selectedIndex == currentIndex) Me_Pink else Color.Transparent
            )
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(100 / 133f),
                contentScale = ContentScale.FillBounds
            )
        }
        Text(
            text = title,
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            color = Nero,
            fontSize = 13.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            lineHeight = 20.dp.toSp()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PerformanceDetailCardPreview() {
    PerformanceDetailCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        painter = painterResource(R.drawable.img_poster),
        title = "드림하이",
        rate = 4.89f,
        numberOfTotal = 324,
        period = "2023.6.1 - 2023.6.18",
        runningTime = "200분",
        date = "",
        location = "LG아트센터 서울"
    )
}

@Preview(showBackground = true)
@Composable
private fun PerformanceCardPreview() {
    PerformanceCard(
        modifier = Modifier.width(120.dp),
        title = "데스노트",
        painter = painterResource(R.drawable.dummy_poster),
        rate = 4.89f,
        numberOfTotal = 1012,
        isShowMetadata = true,
        meta = "D-2"
    )
}
