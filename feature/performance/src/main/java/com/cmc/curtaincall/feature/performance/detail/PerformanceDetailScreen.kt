package com.cmc.curtaincall.feature.performance.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.basic.TopAppBarWithBack
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Black
import com.cmc.curtaincall.common.design.theme.Bright_Gray
import com.cmc.curtaincall.common.design.theme.Cetacean_Blue
import com.cmc.curtaincall.common.design.theme.Chinese_Black
import com.cmc.curtaincall.common.design.theme.Me_Pink
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo
import com.cmc.curtaincall.common.utility.extensions.toChangeFullDate
import com.cmc.curtaincall.common.utility.extensions.toRunningTime
import com.cmc.curtaincall.feature.performance.lostitem.PerformanceLostItemTabScreen
import com.cmc.curtaincall.feature.performance.review.PerformanceReviewTabScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController

enum class TabType(val label: String) {
    DETAIL("상세 정보"), REVIEW("한 줄 리뷰"), LOST_ITEM("분실물")
}

@Composable
internal fun PerformanceDetailScreen(
    performanceDetailViewModel: PerformanceDetailViewModel = hiltViewModel(),
    showId: String,
    onNavigateReview: (String) -> Unit,
    onNavigateLostItem: () -> Unit,
    onBack: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Black)

    LaunchedEffect(Unit) {
        performanceDetailViewModel.requestShowDetail(showId)
        performanceDetailViewModel.requestShowReviewList(showId)
    }

    val scrollState = rememberScrollState()
    val performanceDetailUiState by performanceDetailViewModel.uiState.collectAsStateWithLifecycle()
    val ticketPrices = performanceDetailUiState.showDetailModel.ticketPrice.split(", ")
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        contentAlignment = Alignment.TopCenter
    ) {
        PerformanceDetailContent(
            modifier = Modifier
                .fillMaxWidth()
                .height(666.dp)
                .background(Cetacean_Blue.copy(0.8f)),
            posterUrl = performanceDetailUiState.showDetailModel.poster,
            title = performanceDetailUiState.showDetailModel.name,
            genre = performanceDetailUiState.showDetailModel.genre,
            reviewCount = performanceDetailUiState.showDetailModel.reviewCount,
            reviewGradeSum = performanceDetailUiState.showDetailModel.reviewGradeSum,
            date = "${performanceDetailUiState.showDetailModel.startDate.toChangeFullDate()} - ${performanceDetailUiState.showDetailModel.endDate.toChangeFullDate()}",
            runningTime = if (performanceDetailUiState.showDetailModel.runtime.isEmpty()) "해당 정보 없음" else "${performanceDetailUiState.showDetailModel.runtime.toRunningTime()}분",
            age = performanceDetailUiState.showDetailModel.age,
            ticketPrice = if (performanceDetailUiState.showDetailModel.ticketPrice.isEmpty()) "해당 정보 없음" else ticketPrices.joinToString("\n"),
            facilityName = performanceDetailUiState.showDetailModel.facilityName,
            onBack = onBack
        )
        Column(
            modifier = Modifier
                .offset(y = (-1 * (2 - ticketPrices.size) * 22).dp)
                .padding(top = 644.dp)
                .fillMaxSize()
                .background(White, RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
        ) {
            PerformanceDetailTab(
                performanceDetailViewModel = performanceDetailViewModel,
                Modifier.padding(top = 26.dp),
                showId = showId,
                onNavigateReview = onNavigateReview,
                onNavigateLostItem = onNavigateLostItem
            )
        }
    }
}

@Composable
private fun PerformanceDetailTab(
    performanceDetailViewModel: PerformanceDetailViewModel,
    modifier: Modifier = Modifier,
    showId: String,
    onNavigateReview: (String) -> Unit,
    onNavigateLostItem: () -> Unit
) {
    val performanceDetailUiState by performanceDetailViewModel.uiState.collectAsStateWithLifecycle()
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
                            if (tabType == TabType.DETAIL) {
                                R.drawable.ic_detail_home_sel
                            } else {
                                R.drawable.ic_detail_home
                            }
                        ),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
                Text(
                    text = TabType.DETAIL.label,
                    modifier = Modifier.padding(top = 10.dp),
                    color = if (tabType == TabType.DETAIL) Cetacean_Blue else Bright_Gray,
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
                    color = if (tabType == TabType.REVIEW) Cetacean_Blue else Bright_Gray,
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
                    color = if (tabType == TabType.LOST_ITEM) Cetacean_Blue else Bright_Gray,
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
                        .padding(top = 50.dp),
                    introductionImage = performanceDetailUiState.showDetailModel.introductionImages.firstOrNull(),
                    facilityDetailModel = performanceDetailUiState.facilityDetailModel
                )
            }

            TabType.REVIEW -> {
                PerformanceReviewTabScreen(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 50.dp),
                    showId = showId,
                    reviewCount = performanceDetailUiState.showDetailModel.reviewCount,
                    showReviews = performanceDetailUiState.showReviews,
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
    posterUrl: String? = null,
    genre: String,
    title: String,
    reviewCount: Int,
    reviewGradeSum: Int,
    date: String,
    runningTime: String,
    age: String,
    ticketPrice: String,
    facilityName: String,
    onBack: () -> Unit
) {
    Box(modifier) {
        AsyncImage(
            model = posterUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .blur(50.dp)
                .alpha(0.3f),
            contentScale = ContentScale.FillBounds
        )
        TopAppBarWithBack(
            title = title,
            containerColor = Color.Transparent,
            contentColor = White,
            textModifier = Modifier.width(160.dp),
            onClick = onBack
        )
        PerformanceDetailInfoContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 54.dp),
            posterUrl = posterUrl,
            genre = genre,
            title = title,
            reviewCount = reviewCount,
            reviewGradeSum = reviewGradeSum,
            date = date,
            runningTime = runningTime,
            age = age,
            ticketPrice = ticketPrice,
            facilityName = facilityName
        )
    }
}

@Composable
private fun PerformanceDetailInfoContent(
    modifier: Modifier = Modifier,
    posterUrl: String? = null,
    genre: String,
    title: String,
    reviewCount: Int,
    reviewGradeSum: Int,
    date: String,
    runningTime: String,
    age: String,
    ticketPrice: String,
    facilityName: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = posterUrl,
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
                modifier = Modifier.fillMaxWidth(),
                genre = genre,
                title = title,
                reviewCount = reviewCount,
                reviewGradeSum = reviewGradeSum
            )
            PerformanceDetailBody(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                date = date,
                runningTime = runningTime,
                age = age,
                ticketPrice = ticketPrice,
                facilityName = facilityName
            )
        }
    }
}

@Composable
private fun PerformanceDetailBody(
    modifier: Modifier = Modifier,
    date: String,
    runningTime: String,
    age: String,
    ticketPrice: String,
    facilityName: String
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
                text = date,
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
                text = runningTime,
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
                text = age,
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
                text = ticketPrice,
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
                text = facilityName,
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
    modifier: Modifier = Modifier,
    genre: String,
    title: String,
    reviewCount: Int,
    reviewGradeSum: Int
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
                text = if (genre == "PLAY") "연극" else "뮤지컬",
                color = Chinese_Black,
                fontSize = 13.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            )
        }
        Row(Modifier.padding(top = 10.dp)) {
            Column(Modifier.weight(1f)) {
                Text(
                    text = title,
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
                    Icon(
                        painter = painterResource(R.drawable.ic_star),
                        contentDescription = null,
                        modifier = Modifier.size(14.dp),
                        tint = Me_Pink
                    )
                    Text(
                        text = if (reviewCount == 0) {
                            "0.0 (0)"
                        } else {
                            String.format(
                                "%.1f (%d)",
                                reviewGradeSum / reviewCount.toFloat(),
                                reviewCount
                            )
                        },
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
                    .padding(start = 6.dp)
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
