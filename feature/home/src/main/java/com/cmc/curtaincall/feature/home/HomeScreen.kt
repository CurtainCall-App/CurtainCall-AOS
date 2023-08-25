package com.cmc.curtaincall.feature.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.content.card.LiveTalkContentCard
import com.cmc.curtaincall.common.design.component.content.card.MyContentCard
import com.cmc.curtaincall.common.design.component.content.card.PerformanceCard
import com.cmc.curtaincall.common.design.component.content.row.ContentTitleRow
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.*
import com.cmc.curtaincall.common.utility.extensions.toDateWithDay
import com.cmc.curtaincall.common.utility.extensions.toDday
import com.cmc.curtaincall.common.utility.extensions.toTime
import com.cmc.curtaincall.feature.home.guide.GuideType
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    onNavigateGuide: (GuideType) -> Unit,
    onNavigatePerformanceDetail: (String) -> Unit,
    onNavigateLiveTalk: () -> Unit,
    onNavigatePartyMember: () -> Unit,
    onNavigateMyPage: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Cetacean_Blue)

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.home_appbar_title),
                    color = White,
                    fontSize = 18.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = avenirnext
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .background(Cetacean_Blue)
                .padding(top = 20.dp, bottom = 10.dp),
            actions = {},
            colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Cetacean_Blue)
        )
    }) { paddingValues ->
        HomeContent(
            homeViewModel = homeViewModel,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(White),
            onNavigateGuide = onNavigateGuide,
            onNavigatePerformanceDetail = onNavigatePerformanceDetail
        )
    }
}

@Composable
private fun HomeSearchContent(
    homeViewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val searchWords = homeViewModel.uiState.collectAsStateWithLifecycle().value.searchWords
    Column(modifier.verticalScroll(scrollState)) {
        searchWords.forEach { searchWord ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp, horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = searchWord.word,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    painter = painterResource(R.drawable.ic_close),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
private fun HomeContent(
    homeViewModel: HomeViewModel,
    modifier: Modifier = Modifier,
    onNavigateGuide: (GuideType) -> Unit,
    onNavigatePerformanceDetail: (String) -> Unit
) {
    val scrollState = rememberScrollState()
    val homeUiState by homeViewModel.uiState.collectAsStateWithLifecycle()
    Column(modifier.verticalScroll(scrollState)) {
        HomeBanner(
            modifier = Modifier
                .height(275.dp)
                .background(Cetacean_Blue),
            nickname = homeUiState.nickname,
            onNavigateGuide = onNavigateGuide
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(White)
        ) {
            if (homeUiState.myRecruitments.isNotEmpty()) {
                HomeContentRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(top = 40.dp),
                    painter = painterResource(R.drawable.ic_gather),
                    title = stringResource(R.string.home_my_gathering_tab)
                ) {
                    homeUiState.myRecruitments.forEach { myRecruitment ->
                        MyContentCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 12.dp),
                            containColor = Cultured,
                            shape = RoundedCornerShape(10.dp),
                            imageUrl = myRecruitment.showPoster,
                            showName = myRecruitment.showName,
                            description = myRecruitment.title,
                            numberOfPartyMember = myRecruitment.curMemberNum,
                            numberOfTotalMember = myRecruitment.maxMemberNum,
                            date = myRecruitment.showAt.toDateWithDay(),
                            time = myRecruitment.showAt.toTime()
                        )
                    }
                }
            }
            if (homeUiState.myParticipations.isNotEmpty()) {
                HomeContentRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(top = 40.dp),
                    painter = painterResource(R.drawable.ic_my_participation),
                    title = stringResource(R.string.home_my_participation_tab)
                ) {
                    homeUiState.myParticipations.forEach { myParticipation ->
                        MyContentCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 12.dp)
                                .height(97.dp),
                            containColor = Cultured,
                            shape = RoundedCornerShape(10.dp),
                            imageUrl = myParticipation.showPoster,
                            showName = myParticipation.showName,
                            description = myParticipation.title,
                            numberOfPartyMember = myParticipation.curMemberNum,
                            numberOfTotalMember = myParticipation.maxMemberNum,
                            date = myParticipation.showAt.toDateWithDay(),
                            time = myParticipation.showAt.toTime()
                        )
                    }
                }
            }
            if (homeUiState.liveTalks.isNotEmpty()) {
                HomeContentRow(
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .fillMaxWidth()
                        .background(Cultured)
                        .padding(vertical = 20.dp),
                    titleModifier = Modifier.padding(start = 20.dp),
                    painter = painterResource(R.drawable.ic_chatting),
                    title = stringResource(R.string.home_coming_livetalk)
                ) {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp)
                    ) {
                        itemsIndexed(List(10) {}) { index, item ->
                            if (index == 0) Spacer(Modifier.size(20.dp))
                            Row {
                                LiveTalkContentCard(
                                    modifier = Modifier.width(72.dp),
                                    title = "비스타",
                                    painter = painterResource(R.drawable.img_poster),
                                    time = "19:30"
                                )
                                Spacer(Modifier.size(9.dp))
                            }
                        }
                    }
                }
            }
            if (homeUiState.showRanks.isNotEmpty()) {
                HomeContentRow(
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .fillMaxWidth(),
                    titleModifier = Modifier.padding(start = 20.dp),
                    painter = painterResource(R.drawable.ic_fire),
                    title = stringResource(R.string.home_top10_popular_performance)
                ) {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp)
                    ) {
                        itemsIndexed(homeUiState.showRanks) { index, showRank ->
                            if (index == 0) Spacer(Modifier.size(20.dp))
                            Row {
                                PerformanceCard(
                                    modifier = Modifier.width(120.dp),
                                    title = showRank.name,
                                    painter = painterResource(R.drawable.ic_error_poster),
                                    imageUrl = showRank.poster,
                                    rate = if (showRank.reviewCount == 0) 0.0f else showRank.reviewGradeSum / showRank.reviewCount.toFloat(),
                                    numberOfTotal = showRank.reviewCount,
                                    isShowMetadata = true,
                                    meta = (index + 1).toString(),
                                    onClick = { onNavigatePerformanceDetail(showRank.id) }
                                )
                                Spacer(Modifier.size(12.dp))
                            }
                        }
                    }
                }
            }
            if (homeUiState.openShowInfos.isNotEmpty()) {
                HomeContentRow(
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .fillMaxWidth(),
                    titleModifier = Modifier.padding(start = 20.dp),
                    painter = painterResource(R.drawable.ic_open_clock),
                    title = stringResource(R.string.home_scheduled_open_performance)
                ) {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp)
                    ) {
                        itemsIndexed(homeUiState.openShowInfos) { index, openShowInfo ->
                            if (index == 0) Spacer(Modifier.size(20.dp))
                            Row {
                                PerformanceCard(
                                    modifier = Modifier.width(120.dp),
                                    title = openShowInfo.name,
                                    imageUrl = openShowInfo.poster,
                                    painter = painterResource(R.drawable.ic_error_poster),
                                    rate = if (openShowInfo.reviewCount == 0) 0.0f else openShowInfo.reviewGradeSum / openShowInfo.reviewCount.toFloat(),
                                    numberOfTotal = openShowInfo.reviewCount,
                                    isShowMetadata = true,
                                    meta = if (openShowInfo.startDate.toDday() == 0L) "D-DAY" else "D${openShowInfo.startDate.toDday()}",
                                    onClick = { onNavigatePerformanceDetail(openShowInfo.id) }
                                )
                                Spacer(Modifier.size(12.dp))
                            }
                        }
                    }
                }
            }
            if (homeUiState.cheapShowInfos.isNotEmpty()) {
                HomeContentRow(
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .fillMaxWidth(),
                    titleModifier = Modifier.padding(start = 20.dp),
                    painter = painterResource(R.drawable.ic_value_of_money),
                    title = stringResource(R.string.home_value_for_money_performance)
                ) {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp)
                    ) {
                        itemsIndexed(List(10) {}) { index, item ->
                            if (index == 0) Spacer(Modifier.size(20.dp))
                            Row {
                                PerformanceCard(
                                    modifier = Modifier.width(120.dp),
                                    title = "데스노트",
                                    painter = painterResource(R.drawable.dummy_poster),
                                    rate = 4.89f,
                                    numberOfTotal = 1012
                                )
                                Spacer(Modifier.size(12.dp))
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(68.dp))
        }
    }
}

@Composable
internal fun HomeContentRow(
    modifier: Modifier = Modifier,
    titleModifier: Modifier = Modifier,
    painter: Painter,
    title: String,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        ContentTitleRow(
            modifier = titleModifier,
            painter = painter,
            title = title
        )
        content()
    }
}

private data class BannerItem(
    val title: String,
    val description: String,
    val color: Color,
    val content: @Composable BoxScope.() -> Unit,
    val onClick: () -> Unit
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun HomeBanner(
    modifier: Modifier = Modifier,
    nickname: String,
    onNavigateGuide: (GuideType) -> Unit
) {
    val bannerItems = listOf(
        BannerItem(title = stringResource(R.string.home_banner_word_dictionary_title), description = stringResource(R.string.home_banner_word_dictionary_description), color = Navajo_White, content = {
            Image(
                painter = painterResource(R.drawable.ic_dictionary),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 17.dp, bottom = (15.27).dp)
            )
        }, onClick = { onNavigateGuide(GuideType.DICTIONARY) }),
        BannerItem(title = stringResource(R.string.home_banner_ticketing_guide_title), description = stringResource(R.string.home_banner_ticketing_guide_description), color = Pale_Lavendar, content = {
            Image(
                painter = painterResource(R.drawable.ic_ticket),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 20.dp, bottom = (18.37).dp)
            )
        }, onClick = { onNavigateGuide(GuideType.TICKETING) }),
        BannerItem(title = stringResource(R.string.home_banner_gift_title), description = stringResource(R.string.home_banner_gift_description), color = Water, content = {
            Image(
                painter = painterResource(R.drawable.ic_gift),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = (13.5).dp, bottom = (9.79).dp)
            )
        }, onClick = { onNavigateGuide(GuideType.DISCOUNT) })
    )

    val pagerState = rememberPagerState { bannerItems.size }
    Column(modifier) {
        Text(
            text = "안녕하세요 ${nickname}님",
            modifier = Modifier.padding(
                top = 22.dp,
                start = 20.dp
            ),
            color = White,
            fontSize = 18.dp.toSp(),
            fontWeight = FontWeight.Bold,
            fontFamily = spoqahansanseeo
        )
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 14.dp)
                .height(170.dp)
        ) { position ->
            HomeBannerPagerItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .background(bannerItems[position].color, RoundedCornerShape(12.dp)),
                title = bannerItems[position].title,
                description = bannerItems[position].description,
                content = bannerItems[position].content,
                onClick = bannerItems[position].onClick
            )
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            pageCount = bannerItems.size,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 14.dp),
            activeColor = White,
            inactiveColor = White.copy(alpha = 0.2f),
            indicatorWidth = 7.dp,
            indicatorHeight = 7.dp,
            spacing = 5.dp
        )
    }
}

@Composable
private fun HomeBannerPagerItem(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    content: @Composable BoxScope.() -> Unit = {},
    onClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(modifier.clickable { onClick() }) {
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .padding(top = 18.dp, bottom = 16.dp)
            ) {
                Text(
                    text = title,
                    color = Cetacean_Blue,
                    fontSize = 20.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )
                Text(
                    text = description,
                    modifier = Modifier.padding(top = 10.dp),
                    color = Cetacean_Blue,
                    fontSize = 13.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    lineHeight = 17.dp.toSp()
                )
                Spacer(Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .background(Cetacean_Blue, RoundedCornerShape(16.dp))
                        .padding(horizontal = 12.dp, vertical = 4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.home_banner_showing),
                        color = White,
                        fontSize = 14.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo
                    )
                }
            }
            content()
        }
    }
}
