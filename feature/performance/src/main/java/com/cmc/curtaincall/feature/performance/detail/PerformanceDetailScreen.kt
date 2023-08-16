package com.cmc.curtaincall.feature.performance.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.basic.TopAppBarWithBack
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Black
import com.cmc.curtaincall.common.design.theme.Bright_Gray
import com.cmc.curtaincall.common.design.theme.Cetacean_Blue
import com.cmc.curtaincall.common.design.theme.Chinese_Black
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo
import com.cmc.curtaincall.feature.performance.lostitem.PerformanceLostItemTabScreen
import com.cmc.curtaincall.feature.performance.review.PerformanceReviewTabScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController

enum class TabType(val label: String) {
    DETAIL("상세 정보"), REVIEW("한 줄 리뷰"), LOST_ITEM("분실물")
}

@Composable
internal fun PerformanceDetailScreen(
    onNavigateReview: () -> Unit,
    onNavigateLostItem: () -> Unit,
    onBack: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Black)

    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        contentAlignment = Alignment.TopCenter
    ) {
        PerformanceDetailContent(
            modifier = Modifier
                .fillMaxWidth()
                .height(668.dp)
                .background(Cetacean_Blue.copy(0.8f)),
            onBack = onBack
        )
        Column(
            modifier = Modifier
                .padding(top = 644.dp)
                .fillMaxSize()
                .background(White, RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
        ) {
            PerformanceDetailTab(
                Modifier.padding(top = 26.dp),
                onNavigateReview = onNavigateReview,
                onNavigateLostItem = onNavigateLostItem
            )
        }
    }
}

@Composable
private fun PerformanceDetailTab(
    modifier: Modifier = Modifier,
    onNavigateReview: () -> Unit,
    onNavigateLostItem: () -> Unit
) {
    var tabType by remember { mutableStateOf(TabType.DETAIL) }
    Column(modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(
                    onClick = { tabType = TabType.DETAIL },
                    modifier = Modifier
                        .size(58.dp)
                        .clip(CircleShape),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = if (tabType == TabType.DETAIL) Cetacean_Blue else Bright_Gray
                    )
                ) {
                    Icon(
                        painter = painterResource(
                            if (tabType == TabType.DETAIL) R.drawable.ic_detail_home_sel else R.drawable.ic_detail_home
                        ),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
                Text(
                    text = TabType.DETAIL.label,
                    modifier = Modifier.padding(top = 10.dp),
                    color = Chinese_Black,
                    fontSize = 15.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(
                    onClick = { tabType = TabType.REVIEW },
                    modifier = Modifier
                        .size(58.dp)
                        .clip(CircleShape),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = if (tabType == TabType.REVIEW) Cetacean_Blue else Bright_Gray
                    )
                ) {
                    Icon(
                        painter = painterResource(
                            if (tabType == TabType.REVIEW) R.drawable.ic_review_sel else R.drawable.ic_review
                        ),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
                Text(
                    text = TabType.REVIEW.label,
                    modifier = Modifier.padding(top = 10.dp),
                    color = Chinese_Black,
                    fontSize = 15.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(
                    onClick = { tabType = TabType.LOST_ITEM },
                    modifier = Modifier
                        .size(58.dp)
                        .clip(CircleShape),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = if (tabType == TabType.LOST_ITEM) Cetacean_Blue else Bright_Gray
                    )
                ) {
                    Icon(
                        painter = painterResource(
                            if (tabType == TabType.LOST_ITEM) R.drawable.ic_lost_item_sel else R.drawable.ic_lost_item
                        ),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
                Text(
                    text = TabType.LOST_ITEM.label,
                    modifier = Modifier.padding(top = 10.dp),
                    color = Chinese_Black,
                    fontSize = 15.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
            }
        }

        when (tabType) {
            TabType.DETAIL -> {
                PerformanceDetailTabScreen(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 50.dp)
                )
            }

            TabType.REVIEW -> {
                PerformanceReviewTabScreen(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 50.dp),
                    onNavigateReview = onNavigateReview
                )
            }

            TabType.LOST_ITEM -> {
                PerformanceLostItemTabScreen(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 50.dp),
                    onNavigateLostItem = onNavigateLostItem
                )
            }
        }
    }
}

@Composable
private fun PerformanceDetailContent(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    Box(modifier) {
        Image(
            painter = painterResource(R.drawable.img_poster),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .blur(50.dp)
                .alpha(0.3f),
            contentScale = ContentScale.FillBounds
        )
        TopAppBarWithBack(
            title = "비스티",
            containerColor = Color.Transparent,
            contentColor = White,
            onClick = onBack
        )
        PerformanceDetailInfoContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 54.dp)
        )
    }
}

@Composable
private fun PerformanceDetailInfoContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = null,
            error = painterResource(R.drawable.img_poster),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 43.dp)
                .size(160.dp, 212.dp)
                .clip(RoundedCornerShape(15.dp)),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 30.dp)
        ) {
            PerformanceDetailHeader(
                modifier = Modifier.fillMaxWidth()
            )
            PerformanceDetailBody(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
            )
        }
    }
}

@Composable
private fun PerformanceDetailBody(
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Row {
            Text(
                text = stringResource(R.string.performance_detail_period),
                modifier = Modifier.width(66.dp),
                color = White,
                fontWeight = FontWeight.Medium,
                fontSize = 14.dp.toSp(),
                fontFamily = spoqahansanseeo
            )
            Text(
                text = "2023.6.1 - 2023.6.18",
                color = White,
                fontWeight = FontWeight.Medium,
                fontSize = 14.dp.toSp(),
                fontFamily = spoqahansanseeo
            )
        }
        Row(Modifier.padding(top = 4.dp)) {
            Text(
                text = stringResource(R.string.performance_detail_viewing_time),
                modifier = Modifier.width(66.dp),
                color = White,
                fontWeight = FontWeight.Medium,
                fontSize = 14.dp.toSp(),
                fontFamily = spoqahansanseeo
            )
            Text(
                text = "200분",
                color = White,
                fontWeight = FontWeight.Medium,
                fontSize = 14.dp.toSp(),
                fontFamily = spoqahansanseeo
            )
        }
        Row(Modifier.padding(top = 4.dp)) {
            Text(
                text = stringResource(R.string.performance_detail_viewing_age),
                modifier = Modifier.width(66.dp),
                color = White,
                fontWeight = FontWeight.Medium,
                fontSize = 14.dp.toSp(),
                fontFamily = spoqahansanseeo
            )
            Text(
                text = "14세 이상 관람가",
                color = White,
                fontWeight = FontWeight.Medium,
                fontSize = 14.dp.toSp(),
                fontFamily = spoqahansanseeo
            )
        }
        Row(Modifier.padding(top = 4.dp)) {
            Text(
                text = stringResource(R.string.performance_detail_ticket_price),
                modifier = Modifier.width(66.dp),
                color = White,
                fontWeight = FontWeight.Medium,
                fontSize = 14.dp.toSp(),
                fontFamily = spoqahansanseeo
            )
            Text(
                text = "R석 99,000원 | S석 77,000원 |\nA석 44,000원",
                color = White,
                fontWeight = FontWeight.Medium,
                fontSize = 14.dp.toSp(),
                fontFamily = spoqahansanseeo,
                lineHeight = 22.dp.toSp()
            )
        }
        Row(Modifier.padding(top = 4.dp)) {
            Text(
                text = stringResource(R.string.performance_detail_location),
                modifier = Modifier.width(66.dp),
                color = White,
                fontWeight = FontWeight.Medium,
                fontSize = 14.dp.toSp(),
                fontFamily = spoqahansanseeo
            )
            Text(
                text = "LG아트센터 서울 LG SISNATURE 홀",
                color = White,
                fontWeight = FontWeight.Medium,
                fontSize = 14.dp.toSp(),
                fontFamily = spoqahansanseeo
            )
        }
    }
}

@Composable
private fun PerformanceDetailHeader(
    modifier: Modifier = Modifier
) {
    var isSelectBookmark by remember { mutableStateOf(false) }
    Column(modifier) {
        Box(
            modifier = Modifier
                .background(White, RoundedCornerShape(20.dp))
                .padding(horizontal = 10.dp, vertical = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "뮤지컬",
                color = Chinese_Black,
                fontSize = 13.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            )
        }
        Row(Modifier.padding(top = 10.dp)) {
            Column(Modifier.weight(1f)) {
                Text(
                    text = "비스티",
                    color = White,
                    fontSize = 22.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "예매율 29.0% |",
                        modifier = Modifier.padding(end = 8.dp),
                        color = White,
                        fontSize = 13.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo
                    )
                    Icon(
                        painter = painterResource(R.drawable.ic_star),
                        contentDescription = null,
                        modifier = Modifier.size(14.dp),
                        tint = Color.Unspecified
                    )
                    Text(
                        text = "4.8 (324)",
                        modifier = Modifier.padding(start = 2.dp),
                        color = White,
                        fontSize = 13.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo
                    )
                }
            }
            IconButton(
                onClick = { isSelectBookmark = isSelectBookmark.not() },
                modifier = Modifier
                    .clip(CircleShape)
                    .size(30.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = if (isSelectBookmark) Cetacean_Blue else Bright_Gray
                )
            ) {
                Icon(
                    painter = painterResource(if (isSelectBookmark) R.drawable.ic_bookmark_sel else R.drawable.ic_bookmark),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
        }
    }
}
