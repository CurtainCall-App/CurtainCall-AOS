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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.component.appbars.CurtainCallDefaultTopAppBar
import com.cmc.curtaincall.common.designsystem.component.card.LiveTalkContentCard
import com.cmc.curtaincall.common.designsystem.component.card.MyContentCard
import com.cmc.curtaincall.common.designsystem.component.card.PerformanceCard
import com.cmc.curtaincall.common.designsystem.component.row.ContentTitleRow
import com.cmc.curtaincall.common.designsystem.dimension.Paddings
import com.cmc.curtaincall.common.designsystem.theme.*
import com.cmc.curtaincall.common.utility.extensions.toChangeDate
import com.cmc.curtaincall.common.utility.extensions.toDateWithDay
import com.cmc.curtaincall.common.utility.extensions.toDday
import com.cmc.curtaincall.common.utility.extensions.toTime
import com.cmc.curtaincall.domain.model.show.ShowRecommendationModel
import com.cmc.curtaincall.domain.type.HomeGuideMenu
import com.cmc.curtaincall.domain.type.translateShowGenreType
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import io.getstream.chat.android.client.ChatClient

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    chatClient: ChatClient,
    onNavigateGuide: (HomeGuideMenu) -> Unit,
    onNavigatePerformanceDetail: (String) -> Unit,
    onNavigateLiveTalk: () -> Unit = {},
    onNavigatePartyMember: () -> Unit = {},
    onNavigateMyPage: () -> Unit = {}
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(CurtainCallTheme.colors.background)

    LaunchedEffect(Unit) {
        homeViewModel.connectChattingClient(chatClient)
    }

    Scaffold(
        topBar = {
            CurtainCallDefaultTopAppBar()
        }
    ) { paddingValues ->
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
private fun HomeContent(
    homeViewModel: HomeViewModel,
    modifier: Modifier = Modifier,
    onNavigateGuide: (HomeGuideMenu) -> Unit,
    onNavigatePerformanceDetail: (String) -> Unit
) {
    val scrollState = rememberScrollState()
    val homeUiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(homeViewModel) {
        // homeViewModel.requestLiveTalks()
        // homeViewModel.requestMyRecruitments()
        // homeViewModel.requestMyParticipations()
        homeViewModel.requestShowRecommendations()
        homeViewModel.requestPopularShowList()
        homeViewModel.requestOpenShowList()
        homeViewModel.requestEndShowList()
    }

    Column(modifier.verticalScroll(scrollState)) {
        HomeBannerScreen(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .height(346.dp),
            showRecommendations = homeUiState.showRecommendations
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
                            category = myRecruitment.category,
                            title = myRecruitment.title,
                            numberOfPartyMember = myRecruitment.curMemberNum,
                            numberOfTotalMember = myRecruitment.maxMemberNum,
                            description = myRecruitment.content,
                            date = myRecruitment.showAt?.toDateWithDay(),
                            imageUrl = myRecruitment.showPoster,
                            showName = myRecruitment.showName ?: "",
                            time = myRecruitment.showAt?.toTime()
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
                                .padding(top = 12.dp),
                            category = myParticipation.category,
                            title = myParticipation.title,
                            numberOfPartyMember = myParticipation.curMemberNum,
                            numberOfTotalMember = myParticipation.maxMemberNum,
                            description = myParticipation.content,
                            date = myParticipation.showAt.toDateWithDay(),
                            imageUrl = myParticipation.showPoster,
                            showName = myParticipation.showName ?: "",
                            time = myParticipation.showAt.toTime()
                        )
                    }
                }
            }
            if (homeUiState.liveTalks.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .padding(
                            top = if (homeUiState.myRecruitments.isEmpty() && homeUiState.myParticipations.isEmpty()) {
                                0.dp
                            } else {
                                40.dp
                            }
                        )
                        .background(Cultured)
                ) {
                    HomeContentRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = if (homeUiState.myRecruitments.isEmpty() && homeUiState.myParticipations.isEmpty()) {
                                    40.dp
                                } else {
                                    20.dp
                                },
                                bottom = 20.dp
                            ),
                        painter = painterResource(R.drawable.ic_chatting),
                        titleModifier = Modifier.padding(start = 20.dp),
                        title = stringResource(R.string.home_coming_livetalk)
                    ) {
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 12.dp)
                        ) {
                            itemsIndexed(homeUiState.liveTalks) { index, liveTalk ->
                                if (index == 0) Spacer(Modifier.size(20.dp))
                                Row {
                                    LiveTalkContentCard(
                                        modifier = Modifier.width(72.dp),
                                        title = liveTalk.name,
                                        posterUrl = liveTalk.poster,
                                        time = liveTalk.showAt.toTime()
                                    )
                                    Spacer(Modifier.size(12.dp))
                                }
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
                if (homeUiState.endShowInfos.isNotEmpty()) {
                    HomeContentRow(
                        modifier = Modifier
                            .padding(top = 40.dp)
                            .fillMaxWidth(),
                        titleModifier = Modifier.padding(start = 20.dp),
                        painter = painterResource(R.drawable.ic_end_clock),
                        title = stringResource(R.string.home_scheduled_end_performance)
                    ) {
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 12.dp)
                        ) {
                            itemsIndexed(homeUiState.endShowInfos) { index, endShowInfo ->
                                if (index == 0) Spacer(Modifier.size(20.dp))
                                Row {
                                    PerformanceCard(
                                        modifier = Modifier.width(120.dp),
                                        title = endShowInfo.name,
                                        imageUrl = endShowInfo.poster,
                                        painter = painterResource(R.drawable.ic_error_poster),
                                        rate = if (endShowInfo.reviewCount == 0) 0.0f else endShowInfo.reviewGradeSum / endShowInfo.reviewCount.toFloat(),
                                        numberOfTotal = endShowInfo.reviewCount,
                                        isShowMetadata = true,
                                        meta = if (endShowInfo.endDate.toDday() == 0L) "D-DAY" else "D${endShowInfo.endDate.toDday()}",
                                        onClick = { onNavigatePerformanceDetail(endShowInfo.id) }
                                    )
                                    Spacer(Modifier.size(12.dp))
                                }
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun HomeBannerScreen(
    modifier: Modifier = Modifier,
    showRecommendations: List<ShowRecommendationModel>,
    onNavigatePerformanceDetail: (String) -> Unit = {}
) {
    val pagerState = rememberPagerState { showRecommendations.size + 1 }
    HorizontalPager(
        state = pagerState,
        modifier = modifier.clip(RoundedCornerShape(14.dp))
    ) { position ->
        if (position == 0) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(CurtainCallTheme.colors.primary)
                    .padding(top = 20.dp, bottom = 30.dp, start = 24.dp, end = 20.dp)
            ) {
                Row(Modifier.fillMaxWidth()) {
                    Image(
                        painter = painterResource(R.drawable.ic_logo),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .size(56.dp)
                    )
                    Spacer(Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .background(CurtainCallTheme.colors.background.copy(alpha = 0.2f), RoundedCornerShape(20.dp))
                            .padding(vertical = 2.dp, horizontal = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = String.format("%d/%d", 1, pagerState.pageCount),
                            style = CurtainCallTheme.typography.body5.copy(
                                color = White
                            )
                        )
                    }
                }
                Spacer(Modifier.weight(1f))
                Text(
                    text = stringResource(R.string.home_banner_renewal_description),
                    style = CurtainCallTheme.typography.body4.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = White
                    )
                )
                Text(
                    text = stringResource(R.string.home_banner_renewal),
                    modifier = Modifier.padding(top = 20.dp),
                    style = CurtainCallTheme.typography.h2.copy(
                        color = CurtainCallTheme.colors.secondary
                    )
                )
            }
        } else {
            val brush = Brush.verticalGradient(listOf(Black.copy(alpha = 0f), Black.copy(alpha = 0.4f)))
            val showRecommendation = showRecommendations[position - 1]
            Box(Modifier.fillMaxSize()) {
                Canvas(
                    modifier = Modifier.fillMaxSize(),
                    onDraw = {
                        drawRect(brush)
                    }
                )
                AsyncImage(
                    model = "https://www.kopis.or.kr/upload/pfmPoster/PF_PF121682_210322_143051.gif",
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .blur(80.dp),
                    contentScale = ContentScale.FillBounds
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 20.dp, end = 20.dp)
                        .background(CurtainCallTheme.colors.background.copy(alpha = 0.2f), RoundedCornerShape(20.dp))
                        .padding(vertical = 2.dp, horizontal = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = String.format("%d/%d", position + 1, pagerState.pageCount),
                        style = CurtainCallTheme.typography.body5.copy(
                            color = White
                        )
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = "https://www.kopis.or.kr/upload/pfmPoster/PF_PF121682_210322_143051.gif",
                        contentDescription = null,
                        modifier = Modifier
                            .size(137.dp, 182.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.FillBounds
                    )
                    Row(
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .background(CurtainCallTheme.colors.secondary, RoundedCornerShape(20.dp))
                                .padding(vertical = 2.dp, horizontal = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(R.string.home_banner_recommendation),
                                style = CurtainCallTheme.typography.caption.copy(
                                    color = CurtainCallTheme.colors.primary
                                )
                            )
                        }
                        Box(
                            modifier = Modifier
                                .padding(start = 4.dp)
                                .background(CurtainCallTheme.colors.primary, RoundedCornerShape(20.dp))
                                .padding(vertical = 2.dp, horizontal = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = translateShowGenreType(showRecommendation.genre).value,
                                style = CurtainCallTheme.typography.caption.copy(
                                    color = CurtainCallTheme.colors.onPrimary
                                )
                            )
                        }
                    }
                    Text(
                        text = showRecommendation.description,
                        modifier = Modifier.padding(top = Paddings.xlarge),
                        style = CurtainCallTheme.typography.body5.copy(
                            color = White
                        ),
                        maxLines = 1
                    )
                    Text(
                        text = showRecommendation.name,
                        modifier = Modifier.padding(top = Paddings.xsmall),
                        style = CurtainCallTheme.typography.subTitle2.copy(
                            color = White
                        ),
                        maxLines = 1
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = String.format("%s - %s", showRecommendation.startDate.toChangeDate(), showRecommendation.endDate.toChangeDate()),
                        style = CurtainCallTheme.typography.caption.copy(
                            color = White
                        ),
                        maxLines = 1
                    )
                }
            }
        }
    }
}
