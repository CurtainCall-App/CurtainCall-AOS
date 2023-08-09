package com.cmc.curtaincall.feature.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.CardType
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.*
import com.cmc.curtaincall.feature.home.component.HomeBanner
import com.cmc.curtaincall.feature.home.guide.GuideType
import com.cmc.curtaincall.feature.home.tab.HomeContentTab
import com.cmc.curtaincall.feature.home.tab.HomeLiveTalkTab
import com.cmc.curtaincall.feature.home.tab.HomeMyTab
import com.cmc.curtaincall.feature.home.tab.HomeMyTabItem
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateGuide: (GuideType) -> Unit,
    onNavigatePerformance: () -> Unit,
    onNavigateLiveTalk: () -> Unit,
    onNavigatePartyMember: () -> Unit,
    onNavigateMyPage: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Cetacean_Blue)

    Scaffold(
        topBar = {
            HomeSearchAppbar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                onSearchClick = {
                }
            )
        }
    ) { paddingValues ->
        HomeContent(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            onNavigateGuide = onNavigateGuide
        )
    }
}

@Composable
private fun HomeContent(
    modifier: Modifier = Modifier,
    onNavigateGuide: (GuideType) -> Unit
) {
    val scrollState = rememberScrollState()
    Column(modifier.verticalScroll(scrollState)) {
        HomeBanner(
            modifier = Modifier
                .background(Cetacean_Blue)
                .height(284.dp),
            onNavigateGuide = onNavigateGuide
        )
        HomeMyTab(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 50.dp),
            icon = painterResource(R.drawable.ic_gather),
            title = stringResource(R.string.home_my_gathering_tab)
        ) {
            HomeMyTabItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                image = painterResource(R.drawable.img_poster),
                description = "공연 끝나고 같이 근처에서 야식 먹공연 끝나고 같이 근처에서 야식 먹공연 끝나고 같이 근처에서 야식 먹",
                numberOfMember = 2,
                numberOfTotal = 4,
                date = "23.6.24",
                time = "19:30"
            )
        }
        HomeMyTab(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 40.dp),
            icon = painterResource(R.drawable.ic_my_participation),
            title = stringResource(R.string.home_my_participation_tab)
        ) {
            HomeMyTabItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                image = painterResource(R.drawable.img_poster),
                description = "같이 볼 사람들 모여라~ ",
                numberOfMember = 3,
                numberOfTotal = 4,
                date = "23.6.24",
                time = "19:30"
            )
            HomeMyTabItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                image = painterResource(R.drawable.img_poster),
                description = "같이 볼 사람들 모여라~ ",
                numberOfMember = 3,
                numberOfTotal = 4,
                date = "23.6.24",
                time = "19:30"
            )
        }
        HomeLiveTalkTab(
            modifier = Modifier
                .padding(top = 50.dp)
                .fillMaxWidth()
                .height(204.dp)
        )
        HomeContentTab(
            modifier = Modifier
                .padding(top = 50.dp)
                .padding(start = 20.dp)
                .fillMaxWidth(),
            icon = painterResource(R.drawable.ic_fire),
            title = stringResource(R.string.home_top10_popular_performance),
            cardType = CardType.TOP10
        )
        HomeContentTab(
            modifier = Modifier
                .padding(top = 50.dp)
                .padding(start = 20.dp)
                .fillMaxWidth(),
            icon = painterResource(R.drawable.ic_open_clock),
            title = stringResource(R.string.home_scheduled_open_performance),
            cardType = CardType.OPEN_SOON
        )
        HomeContentTab(
            modifier = Modifier
                .padding(top = 50.dp)
                .padding(start = 20.dp)
                .fillMaxWidth(),
            icon = painterResource(R.drawable.ic_value_of_money),
            title = stringResource(R.string.home_value_for_money_performance),
            cardType = CardType.CHEAP
        )
        Spacer(modifier = Modifier.height(50.dp))
    }
}

@Composable
private fun HomeSearchAppbar(
    modifier: Modifier = Modifier,
    onSearchClick: () -> Unit
) {
    Row(
        modifier = modifier.background(Cetacean_Blue),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.home_appbar_title),
            modifier = Modifier.padding(start = 20.dp),
            color = White,
            fontSize = 18.dp.toSp(),
            fontWeight = FontWeight.Bold,
            fontFamily = avenirnext
        )
        Spacer(Modifier.weight(1f))
        IconButton(
            onClick = { onSearchClick() },
            modifier = Modifier
                .padding(end = 20.dp)
                .size(24.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_search),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = White
            )
        }
    }
}
