package com.cmc.curtaincall.feature.performance

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.component.basic.CurtainCallSelectTypeButton
import com.cmc.curtaincall.common.designsystem.component.basic.SearchAppBar
import com.cmc.curtaincall.common.designsystem.component.basic.TopAppBarOnlySearch
import com.cmc.curtaincall.common.designsystem.component.content.card.PerformanceDetailCard
import com.cmc.curtaincall.common.designsystem.component.content.row.SortTypeRow
import com.cmc.curtaincall.common.designsystem.component.custom.SelectSortTypeBottomSheet
import com.cmc.curtaincall.common.designsystem.component.items.EmptyItem
import com.cmc.curtaincall.common.designsystem.component.items.SearchItem
import com.cmc.curtaincall.common.designsystem.extensions.toSp
import com.cmc.curtaincall.common.designsystem.theme.*
import com.cmc.curtaincall.common.utility.extensions.ShowDay
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

    val performanceUiState by performanceViewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            if (performanceUiState.isActiveSearch) {
                SearchAppBar(
                    value = performanceUiState.queryString,
                    onValueChange = {
                        performanceViewModel.setQueryString(it)
                        performanceViewModel.changeDoneSearch(false)
                    },
                    containerColor = White,
                    contentColor = Nero,
                    placeholder = stringResource(R.string.search_performance_title),
                    onDone = {
                        if (performanceUiState.queryString.isNotEmpty()) {
                            performanceViewModel.searchShowList(performanceUiState.queryString)
                            performanceViewModel.insertShowSearchWord()
                        }
                        performanceViewModel.changeDoneSearch(true)
                    },
                    onClick = {
                        performanceViewModel.changeActiveSearch(false)
                        performanceViewModel.setQueryString("")
                    },
                    onAction = { performanceViewModel.setQueryString("") }
                )
            } else {
                TopAppBarOnlySearch(
                    containerColor = White,
                    contentColor = Roman_Silver,
                    onClick = { performanceViewModel.changeActiveSearch(true) }
                )
            }
        }
    ) { paddingValues ->
        if (performanceUiState.isActiveSearch) {
            PerformanceSearchContent(
                performanceViewModel = performanceViewModel,
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(White),
                onClick = {
                    performanceViewModel.setQueryString(it)
                    performanceViewModel.searchShowList(it)
                    performanceViewModel.insertShowSearchWord()
                    performanceViewModel.changeDoneSearch(true)
                },
                onNavigateDetail = onNavigateDetail
            )
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
private fun PerformanceSearchContent(
    performanceViewModel: PerformanceViewModel,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
    onNavigateDetail: (String) -> Unit
) {
    val searchWords by performanceViewModel.searchWords.collectAsStateWithLifecycle()
    val performanceUiState by performanceViewModel.uiState.collectAsStateWithLifecycle()
    val showSearchItems = performanceUiState.showSearchItems.collectAsLazyPagingItems()
    LazyColumn(modifier) {
        item {
            Spacer(
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Cultured)
            )
        }

        if (performanceUiState.queryString.isEmpty()) {
            if (searchWords.isEmpty()) {
                item {
                    EmptyItem(
                        modifier = Modifier
                            .padding(top = 232.dp)
                            .fillMaxWidth(),
                        alert = stringResource(R.string.search_empty_recently_word)
                    )
                }
            } else {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .padding(bottom = 20.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(R.string.search_recently_word),
                                modifier = Modifier.weight(1f),
                                color = Chinese_Black,
                                fontSize = 16.dp.toSp(),
                                fontWeight = FontWeight.Bold,
                                fontFamily = spoqahansanseeo
                            )
                            Text(
                                text = stringResource(R.string.search_delete_recentyl_word),
                                modifier = Modifier.clickable { performanceViewModel.deleteShowSearchWordList() },
                                color = Roman_Silver,
                                fontSize = 14.dp.toSp(),
                                fontWeight = FontWeight.Medium,
                                fontFamily = spoqahansanseeo
                            )
                        }
                        searchWords.forEach { searchWord ->
                            SearchItem(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp),
                                word = searchWord.word,
                                onClick = { onClick(searchWord.word) },
                                onDelete = { performanceViewModel.deleteShowSearchWord(searchWord) }
                            )
                        }
                    }
                }
            }
        } else {
            if (performanceUiState.isDoneSearch) {
                itemsIndexed(showSearchItems) { index, showInfoModel ->
                    showInfoModel?.let { showInfoModel ->
                        PerformanceDetailCard(
                            modifier = Modifier
                                .padding(horizontal = 20.dp)
                                .fillMaxWidth()
                                .padding(vertical = 12.dp),
                            imageUrl = showInfoModel.poster,
                            painter = painterResource(R.drawable.ic_error_poster),
                            title = showInfoModel.name,
                            rate = if (showInfoModel.reviewCount == 0) 0.0f else (showInfoModel.reviewGradeSum / showInfoModel.reviewCount.toFloat()),
                            numberOfTotal = showInfoModel.reviewCount,
                            period = "${showInfoModel.startDate.toChangeDate()}-${showInfoModel.endDate.toChangeDate()}",
                            runningTime = if (showInfoModel.runtime.isEmpty()) "해당 정보 없음" else "${showInfoModel.runtime.toRunningTime()}분",
                            date = showInfoModel.showTimes.map {
                                when (it.dayOfWeek) {
                                    ShowDay.Monday.dayOfWeek -> ShowDay.Monday
                                    ShowDay.Tuesday.dayOfWeek -> ShowDay.Tuesday
                                    ShowDay.Wednesday.dayOfWeek -> ShowDay.Wednesday
                                    ShowDay.Thursday.dayOfWeek -> ShowDay.Thursday
                                    ShowDay.Friday.dayOfWeek -> ShowDay.Friday
                                    ShowDay.Saturday.dayOfWeek -> ShowDay.Saturday
                                    else -> ShowDay.Sunday
                                }
                            }.sortedBy { it.id }.toSet().joinToString(", ") { it.label },
                            location = showInfoModel.facilityName,
                            onClick = { onNavigateDetail(showInfoModel.id) },
                            isFavorite = showInfoModel.favorite,
                            onFavorite = { performanceViewModel.requestFavoriteShow(showInfoModel.id) },
                            onDisFavorite = { performanceViewModel.deleteFavoriteShow(showInfoModel.id) }
                        )
                        if (index != showSearchItems.itemCount) {
                            Spacer(
                                modifier = Modifier
                                    .padding(vertical = 16.dp, horizontal = 20.dp)
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

@Composable
private fun PerformanceContent(
    performanceViewModel: PerformanceViewModel,
    modifier: Modifier = Modifier,
    onNavigateDetail: (String) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    val performanceUiState by performanceViewModel.uiState.collectAsStateWithLifecycle()
    val playItems = performanceUiState.playItems.collectAsLazyPagingItems()
    val musicalItems = performanceUiState.musicalItems.collectAsLazyPagingItems()

    if (showDialog) {
        SelectSortTypeBottomSheet(
            sortType = performanceUiState.sortType,
            onSelectSortType = {
                performanceViewModel.changeSortType(it)
                showDialog = false
            },
            onDismissRequest = { showDialog = false }
        )
    }
    LazyColumn(
        modifier = modifier
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
                    isCheckFirstType = performanceUiState.genre == "PLAY",
                    onTypeChange = { check ->
                        performanceViewModel.changeGenre(
                            if (check) "PLAY" else "MUSICAL"
                        )
                    }
                )
                SortTypeRow(
                    modifier = Modifier.padding(top = 28.dp),
                    sortType = performanceUiState.sortType,
                    onClick = { showDialog = true }
                )
            }
        }

        if (performanceUiState.genre == "PLAY") {
            itemsIndexed(playItems) { index, showInfoModel ->
                showInfoModel?.let { showInfoModel ->
                    PerformanceDetailCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp),
                        imageUrl = showInfoModel.poster,
                        painter = painterResource(R.drawable.ic_error_poster),
                        title = showInfoModel.name,
                        rate = if (showInfoModel.reviewCount == 0) 0.0f else (showInfoModel.reviewGradeSum / showInfoModel.reviewCount.toFloat()),
                        numberOfTotal = showInfoModel.reviewCount,
                        period = "${showInfoModel.startDate.toChangeDate()}-${showInfoModel.endDate.toChangeDate()}",
                        runningTime = if (showInfoModel.runtime.isEmpty()) "해당 정보 없음" else "${showInfoModel.runtime.toRunningTime()}분",
                        date = showInfoModel.showTimes.map {
                            when (it.dayOfWeek) {
                                ShowDay.Monday.dayOfWeek -> ShowDay.Monday
                                ShowDay.Tuesday.dayOfWeek -> ShowDay.Tuesday
                                ShowDay.Wednesday.dayOfWeek -> ShowDay.Wednesday
                                ShowDay.Thursday.dayOfWeek -> ShowDay.Thursday
                                ShowDay.Friday.dayOfWeek -> ShowDay.Friday
                                ShowDay.Saturday.dayOfWeek -> ShowDay.Saturday
                                else -> ShowDay.Sunday
                            }
                        }.sortedBy { it.id }.toSet().joinToString(", ") { it.label },
                        location = showInfoModel.facilityName,
                        onClick = {
                            performanceViewModel.changeLastIndex(index)
                            onNavigateDetail(showInfoModel.id)
                        },
                        isFavorite = showInfoModel.favorite,
                        onFavorite = { performanceViewModel.requestFavoriteShow(showInfoModel.id) },
                        onDisFavorite = { performanceViewModel.deleteFavoriteShow(showInfoModel.id) }
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
                        painter = painterResource(R.drawable.ic_error_poster),
                        title = showInfoModel.name,
                        rate = if (showInfoModel.reviewCount == 0) 0.0f else (showInfoModel.reviewGradeSum / showInfoModel.reviewCount.toFloat()),
                        numberOfTotal = showInfoModel.reviewCount,
                        period = "${showInfoModel.startDate.toChangeDate()}-${showInfoModel.endDate.toChangeDate()}",
                        runningTime = if (showInfoModel.runtime.isEmpty()) "해당 정보 없음" else "${showInfoModel.runtime.toRunningTime()}분",
                        date = showInfoModel.showTimes.map {
                            when (it.dayOfWeek) {
                                ShowDay.Monday.dayOfWeek -> ShowDay.Monday
                                ShowDay.Tuesday.dayOfWeek -> ShowDay.Tuesday
                                ShowDay.Wednesday.dayOfWeek -> ShowDay.Wednesday
                                ShowDay.Thursday.dayOfWeek -> ShowDay.Thursday
                                ShowDay.Friday.dayOfWeek -> ShowDay.Friday
                                ShowDay.Saturday.dayOfWeek -> ShowDay.Saturday
                                else -> ShowDay.Sunday
                            }
                        }.sortedBy { it.id }.toSet().joinToString(", ") { it.label },
                        location = showInfoModel.facilityName,
                        onClick = {
                            performanceViewModel.changeLastIndex(index)
                            onNavigateDetail(showInfoModel.id)
                        },
                        isFavorite = showInfoModel.favorite,
                        onFavorite = { performanceViewModel.requestFavoriteShow(showInfoModel.id) },
                        onDisFavorite = { performanceViewModel.deleteFavoriteShow(showInfoModel.id) }
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
