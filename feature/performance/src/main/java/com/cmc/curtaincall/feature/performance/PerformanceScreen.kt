package com.cmc.curtaincall.feature.performance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.basic.CurtainCallSelectTypeButton
import com.cmc.curtaincall.common.design.component.basic.SearchAppBar
import com.cmc.curtaincall.common.design.component.basic.TopAppBarOnlySearch
import com.cmc.curtaincall.common.design.component.content.card.PerformanceDetailCard
import com.cmc.curtaincall.common.design.component.content.row.SortTypeRow
import com.cmc.curtaincall.common.design.component.custom.SelectSortTypeBottomSheet
import com.cmc.curtaincall.common.design.component.custom.SortType
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.*
import com.cmc.curtaincall.common.utility.extensions.toChangeDate
import com.cmc.curtaincall.common.utility.extensions.toRunningTime
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerformanceScreen(
    performanceViewModel: PerformanceViewModel = hiltViewModel(),
    onNavigateDetail: (String) -> Unit
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
                performanceViewModel = performanceViewModel,
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
    performanceViewModel: PerformanceViewModel,
    modifier: Modifier = Modifier,
    onNavigateDetail: (String) -> Unit
) {
    var isCheckFirstType by remember { mutableStateOf(true) }
    var sortType by remember { mutableStateOf(SortType.STAR) }
    var showDialog by remember { mutableStateOf(false) }

    val playItems = performanceViewModel.playItems.collectAsLazyPagingItems()
    val musicalItems = performanceViewModel.musicalItems.collectAsLazyPagingItems()

    if (showDialog) {
        SelectSortTypeBottomSheet(
            sortType = sortType,
            onSelectSortType = {
                sortType = it
                showDialog = false
            },
            onDismissRequest = { showDialog = false }
        )
    }
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

            if (isCheckFirstType) {
                itemsIndexed(playItems) { index, showInfoModel ->
                    showInfoModel?.let { showInfoModel ->
                        PerformanceDetailCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp),
                            imageUrl = showInfoModel.poster,
                            painter = painterResource(R.drawable.img_poster),
                            title = showInfoModel.name,
                            rate = if (showInfoModel.reviewCount == 0) 0.0f else (showInfoModel.reviewGradeSum / showInfoModel.reviewCount.toFloat()),
                            numberOfTotal = showInfoModel.reviewCount,
                            period = "${showInfoModel.startDate.toChangeDate()}-${showInfoModel.endDate.toChangeDate()}",
                            runningTime = "${showInfoModel.runtime.toRunningTime()}분",
                            date = "화-금 19:00",
                            location = showInfoModel.facilityName,
                            onClick = { onNavigateDetail(showInfoModel.id) }
                        )
                        if (index != musicalItems.itemCount) {
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
            } else {
                itemsIndexed(musicalItems) { index, showInfoModel ->
                    showInfoModel?.let { showInfoModel ->
                        PerformanceDetailCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp),
                            imageUrl = showInfoModel.poster,
                            painter = painterResource(R.drawable.img_poster),
                            title = showInfoModel.name,
                            rate = if (showInfoModel.reviewCount == 0) 0.0f else (showInfoModel.reviewGradeSum / showInfoModel.reviewCount.toFloat()),
                            numberOfTotal = showInfoModel.reviewCount,
                            period = "${showInfoModel.startDate.toChangeDate()}-${showInfoModel.endDate.toChangeDate()}",
                            runningTime = "${showInfoModel.runtime.toRunningTime()}분",
                            date = "화-금 19:00",
                            location = showInfoModel.facilityName,
                            onClick = { onNavigateDetail(showInfoModel.id) }
                        )
                        if (index != musicalItems.itemCount) {
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
    }
}
