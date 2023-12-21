package com.cmc.curtaincall.feature.show.detail

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.component.basic.SystemUiStatusBar
import com.cmc.curtaincall.common.designsystem.component.basic.TopAppBarWithBack
import com.cmc.curtaincall.common.designsystem.extensions.toSp
import com.cmc.curtaincall.common.designsystem.theme.Black
import com.cmc.curtaincall.common.designsystem.theme.Bright_Gray
import com.cmc.curtaincall.common.designsystem.theme.Cetacean_Blue
import com.cmc.curtaincall.common.designsystem.theme.Chinese_Black
import com.cmc.curtaincall.common.designsystem.theme.Me_Pink
import com.cmc.curtaincall.common.designsystem.theme.White
import com.cmc.curtaincall.common.designsystem.theme.spoqahansanseeo
import com.cmc.curtaincall.common.utility.extensions.toChangeFullDate
import com.cmc.curtaincall.common.utility.extensions.toRunningTime
import com.cmc.curtaincall.domain.enum.ShowDetailMenuTab
import com.cmc.curtaincall.domain.model.show.ShowDetailModel
import com.cmc.curtaincall.feature.show.lostitem.screen.PerformanceLostItemTabScreen
import com.cmc.curtaincall.feature.show.review.ShowReviewMenuScreen

@Composable
internal fun ShowDetailScreen(
    showDetailViewModel: ShowDetailViewModel = hiltViewModel(),
    showId: String?,
    onNavigateReview: (String) -> Unit = {},
    onNavigateLostProperty: (String) -> Unit = {},
    onNavigateDetail: (String) -> Unit = {},
    onBack: () -> Unit = {}
) {
    requireNotNull(showId)
    LaunchedEffect(Unit) {
        showDetailViewModel.requestShowDetail(showId)
        showDetailViewModel.checkFavoriteShows(showId)
        showDetailViewModel.requestShowReviewList(showId)
    }

    val scrollState = rememberScrollState()
    val showDetailState by showDetailViewModel.uiState.collectAsStateWithLifecycle()
    val ticketPrices = showDetailState.showDetailModel.ticketPrice.split(", ")

    SystemUiStatusBar(Black)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        contentAlignment = Alignment.TopCenter
    ) {
        ShowDetailContent(
            modifier = Modifier
                .fillMaxWidth()
                .height((666 + if (ticketPrices.size > 2) (22 * (ticketPrices.size - 2)) else 0).dp)
                .background(Cetacean_Blue.copy(0.8f)),
            showDetailModel = showDetailState.showDetailModel,
            ticketPrices = ticketPrices,
            onBack = onBack
        )
        Column(
            modifier = Modifier
                .padding(top = (644 + (-1 * (2 - ticketPrices.size) * 22)).dp)
                .fillMaxSize()
                .background(White, RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
        ) {
            ShowDetailMenuTab(
                performanceDetailViewModel = showDetailViewModel,
                modifier = Modifier.padding(top = 26.dp),
                showId = showId,
                onNavigateReview = onNavigateReview,
                onNavigateLostProperty = onNavigateLostProperty,
                onNavigateDetail = onNavigateDetail
            )
        }
    }
}

@Composable
private fun ShowDetailMenuTab(
    performanceDetailViewModel: ShowDetailViewModel,
    modifier: Modifier = Modifier,
    showId: String,
    onNavigateReview: (String) -> Unit = {},
    onNavigateLostProperty: (String) -> Unit = {},
    onNavigateDetail: (String) -> Unit = {}
) {
    val performanceDetailUiState by performanceDetailViewModel.uiState.collectAsStateWithLifecycle()
    val menuTabType = performanceDetailUiState.menuTabType
    Column(modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(
                    onClick = { performanceDetailViewModel.changeTabType(ShowDetailMenuTab.DETAIL) },
                    modifier = Modifier
                        .size(58.dp)
                        .clip(CircleShape),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = if (menuTabType == ShowDetailMenuTab.DETAIL) Cetacean_Blue else Bright_Gray
                    )
                ) {
                    Icon(
                        painter = painterResource(if (menuTabType == ShowDetailMenuTab.DETAIL) R.drawable.ic_detail_home_sel else R.drawable.ic_detail_home),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
                Text(
                    text = ShowDetailMenuTab.DETAIL.label,
                    modifier = Modifier.padding(top = 10.dp),
                    color = if (menuTabType == ShowDetailMenuTab.DETAIL) Cetacean_Blue else Bright_Gray,
                    fontSize = 15.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(
                    onClick = { performanceDetailViewModel.changeTabType(ShowDetailMenuTab.REVIEW) },
                    modifier = Modifier
                        .size(58.dp)
                        .clip(CircleShape),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = if (menuTabType == ShowDetailMenuTab.REVIEW) Cetacean_Blue else Bright_Gray
                    )
                ) {
                    Icon(
                        painter = painterResource(
                            if (menuTabType == ShowDetailMenuTab.REVIEW) R.drawable.ic_review_sel else R.drawable.ic_review
                        ),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
                Text(
                    text = ShowDetailMenuTab.REVIEW.label,
                    modifier = Modifier.padding(top = 10.dp),
                    color = if (menuTabType == ShowDetailMenuTab.REVIEW) Cetacean_Blue else Bright_Gray,
                    fontSize = 15.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(
                    onClick = { performanceDetailViewModel.changeTabType(ShowDetailMenuTab.LOST_ITEM) },
                    modifier = Modifier
                        .size(58.dp)
                        .clip(CircleShape),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = if (menuTabType == ShowDetailMenuTab.LOST_ITEM) Cetacean_Blue else Bright_Gray
                    )
                ) {
                    Icon(
                        painter = painterResource(
                            if (menuTabType == ShowDetailMenuTab.LOST_ITEM) R.drawable.ic_lost_item_sel else R.drawable.ic_lost_item
                        ),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
                Text(
                    text = ShowDetailMenuTab.LOST_ITEM.label,
                    modifier = Modifier.padding(top = 10.dp),
                    color = if (menuTabType == ShowDetailMenuTab.LOST_ITEM) Cetacean_Blue else Bright_Gray,
                    fontSize = 15.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
            }
        }

        when (menuTabType) {
            ShowDetailMenuTab.DETAIL -> {
                ShowDetailMenuScreen(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 50.dp),
                    introductionImage = performanceDetailUiState.showDetailModel.introductionImages.firstOrNull(),
                    showTimes = performanceDetailUiState.showDetailModel.showTimes,
                    similarShows = performanceDetailUiState.similarShows,
                    facilityDetailModel = performanceDetailUiState.facilityDetailModel,
                    onNavigateDetail = onNavigateDetail
                )
            }

            ShowDetailMenuTab.REVIEW -> {
                ShowReviewMenuScreen(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 50.dp),
                    showId = showId,
                    reviewCount = performanceDetailUiState.showDetailModel.reviewCount,
                    showReviews = performanceDetailUiState.showReviews
                ) {
                    onNavigateReview(it)
                }
            }

            ShowDetailMenuTab.LOST_ITEM -> {
                PerformanceLostItemTabScreen(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 50.dp),
                    facilityName = performanceDetailUiState.showDetailModel.facilityName,
                    lostItems = performanceDetailUiState.lostItems,
                    onNavigateLostProperty = onNavigateLostProperty
                )
            }
        }
    }
}

@Composable
private fun ShowDetailContent(
    modifier: Modifier = Modifier,
    showDetailViewModel: ShowDetailViewModel = hiltViewModel(),
    showDetailModel: ShowDetailModel = ShowDetailModel(),
    ticketPrices: List<String> = listOf(),
    onBack: () -> Unit = {}
) {
    val date = "${showDetailModel.startDate.toChangeFullDate()} - ${showDetailModel.endDate.toChangeFullDate()}"
    val runningTime = if (showDetailModel.runtime.isEmpty()) "해당 정보 없음" else "${showDetailModel.runtime.toRunningTime()}분"
    val ticketPrice = if (showDetailModel.ticketPrice.isEmpty()) "해당 정보 없음" else ticketPrices.joinToString("\n")
    Box(modifier) {
        AsyncImage(
            model = showDetailModel.poster,
            error = painterResource(R.drawable.ic_error_poster),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .blur(50.dp)
                .alpha(0.3f),
            contentScale = ContentScale.FillBounds
        )
        TopAppBarWithBack(
            title = showDetailModel.name,
            containerColor = Color.Transparent,
            contentColor = White,
            textModifier = Modifier.width(160.dp),
            onClick = onBack
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 54.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = showDetailModel.poster,
                error = painterResource(R.drawable.ic_error_poster),
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
                ShowDetailHeader(
                    modifier = Modifier.fillMaxWidth(),
                    genre = showDetailModel.genre,
                    title = showDetailModel.name,
                    reviewCount = showDetailModel.reviewCount,
                    reviewGradeSum = showDetailModel.reviewGradeSum,
                    isFavorite = showDetailModel.isFavorite,
                    onFavorite = { isFavorite ->
                        if (isFavorite) {
                            showDetailViewModel.deleteFavoriteShow(showDetailModel.id)
                        } else {
                            showDetailViewModel.requestFavoriteShow(showDetailModel.id)
                        }
                    }
                )
                ShowDetailBody(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp),
                    date = date,
                    runningTime = runningTime,
                    age = showDetailModel.age,
                    ticketPrice = ticketPrice,
                    facilityName = showDetailModel.facilityName
                )
            }
        }
    }
}

@Composable
private fun ShowDetailBody(
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
private fun ShowDetailHeader(
    modifier: Modifier = Modifier,
    genre: String,
    title: String,
    reviewCount: Int,
    reviewGradeSum: Int,
    isFavorite: Boolean,
    onFavorite: (Boolean) -> Unit = {}
) {
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
                onClick = { onFavorite(isFavorite) },
                modifier = Modifier
                    .clip(CircleShape)
                    .padding(start = 6.dp)
                    .size(30.dp),
                colors = IconButtonDefaults.iconButtonColors(containerColor = if (isFavorite) Cetacean_Blue else Bright_Gray)
            ) {
                Icon(
                    painter = painterResource(if (isFavorite) R.drawable.ic_bookmark_sel else R.drawable.ic_bookmark),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
        }
    }
}
