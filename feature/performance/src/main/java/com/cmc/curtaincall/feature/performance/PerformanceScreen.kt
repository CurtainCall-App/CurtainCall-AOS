package com.cmc.curtaincall.feature.performance

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.CurtainCallSelectTypeButton
import com.cmc.curtaincall.common.design.component.SearchAppBar
import com.cmc.curtaincall.common.design.component.TopAppBarOnlySearch
import com.cmc.curtaincall.common.design.component.content.card.PerformanceDetailCard
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.holix.android.bottomsheetdialog.compose.BottomSheetDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerformanceScreen(
    onNavigateDetail: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(White)

    var isActiveSearchState by remember { mutableStateOf(false) }
    var queryState by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            if (isActiveSearchState) {
                SearchAppBar(
                    value = queryState,
                    onValueChange = { queryState = it },
                    containerColor = White,
                    contentColor = Nero,
                    placeholder = stringResource(R.string.search_performance_title),
                    onClick = { isActiveSearchState = false }
                )
            } else {
                TopAppBarOnlySearch(
                    containerColor = White,
                    contentColor = Roman_Silver,
                    onClick = { isActiveSearchState = true }
                )
            }
        }
    ) { paddingValues ->
        if (isActiveSearchState) {
            // TODO
        } else {
            PerformanceContent(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(White),
                onNavigateDetail = onNavigateDetail
            )
        }
    }
}

@Composable
private fun PerformanceContent(
    modifier: Modifier = Modifier,
    onNavigateDetail: () -> Unit
) {
    val context = LocalContext.current
    var isCheckFirstType by remember { mutableStateOf(true) }
    var sortType by remember { mutableStateOf(context.getString(R.string.performance_search_rank_sort)) }
    var showDialog by remember { mutableStateOf(false) }

    Column(modifier) {
        LazyColumn(
            modifier = Modifier
                .padding(top = 6.dp)
                .padding(horizontal = 20.dp)
                .fillMaxSize()
        ) {
            item {
                Column {
                    Text(
                        text = stringResource(R.string.performance_search),
                        color = Black,
                        fontSize = 24.dp.toSp(),
                        fontWeight = FontWeight.Bold,
                        fontFamily = spoqahansanseeo
                    )
                    CurtainCallSelectTypeButton(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .fillMaxWidth()
                            .height(45.dp),
                        firstType = stringResource(R.string.partymember_create_classification_theater),
                        lastType = stringResource(R.string.partymember_create_classification_musical),
                        isCheckFirstType = isCheckFirstType,
                        onTypeChange = { isCheckFirstType = it }
                    )
                    SortTypeRow(
                        modifier = Modifier.padding(top = 28.dp),
                        sortType = sortType,
                        onClick = { showDialog = true }
                    )
                }
            }

            itemsIndexed(Array(10) {}) { index, item ->
                PerformanceDetailCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)
                        .clickable { onNavigateDetail() },
                    painter = painterResource(R.drawable.img_poster),
                    title = "드림하이",
                    rate = 4.89f,
                    numberOfTotal = 324,
                    period = "2023.6.1 - 2023.6.18",
                    runningTime = "200분",
                    dates = listOf("화-금 19:00", "토,일 14:00, 19:00"),
                    location = "LG아트센터 서울"
                )
                if (index != 9) {
                    Spacer(
                        modifier = Modifier
                            .padding(vertical = 16.dp)
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Cultured)
                    )
                }
            }
        }
    }
}

@Composable
private fun SortTypeRow(
    modifier: Modifier = Modifier,
    sortType: String,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier.clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = sortType,
            color = Nero,
            fontSize = 13.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo
        )
        Icon(
            painter = painterResource(R.drawable.ic_sort),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 6.dp)
                .size(14.dp, 11.dp),
            tint = Color.Unspecified
        )
    }
}
