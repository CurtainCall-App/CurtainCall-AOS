package com.cmc.curtaincall.feature.show.search

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
import com.cmc.curtaincall.common.designsystem.component.basic.SystemUiStatusBar
import com.cmc.curtaincall.common.designsystem.component.basic.TopAppBarOnlySearch
import com.cmc.curtaincall.common.designsystem.component.card.PerformanceDetailCard
import com.cmc.curtaincall.common.designsystem.component.content.empty.EmptyContent
import com.cmc.curtaincall.common.designsystem.component.custom.SelectSortTypeBottomSheet
import com.cmc.curtaincall.common.designsystem.component.item.search.SearchTextItem
import com.cmc.curtaincall.common.designsystem.component.row.SortTypeRow
import com.cmc.curtaincall.common.designsystem.extensions.toSp
import com.cmc.curtaincall.common.designsystem.theme.*
import com.cmc.curtaincall.common.utility.extensions.ShowDay
import com.cmc.curtaincall.common.utility.extensions.toChangeDate
import com.cmc.curtaincall.common.utility.extensions.toRunningTime
import com.cmc.curtaincall.domain.type.ShowGenreType
import com.cmc.curtaincall.domain.type.ShowSortType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowSearchScreen(
    showSearchViewModel: ShowSearchViewModel = hiltViewModel(),
    onNavigateDetail: (String) -> Unit
) {
    val showSearchUiState by showSearchViewModel.uiState.collectAsStateWithLifecycle()
    SystemUiStatusBar(White)
    Scaffold(
        topBar = {
            if (showSearchUiState.isActiveSearch) {
                SearchAppBar(
                    value = showSearchUiState.queryString,
                    onValueChange = {
                        showSearchViewModel.setQueryString(it)
                        showSearchViewModel.onSearching()
                    },
                    containerColor = White,
                    contentColor = Nero,
                    placeholder = stringResource(R.string.search_performance_title),
                    onDone = {
                        if (showSearchUiState.queryString.trim().isNotEmpty()) {
                            showSearchViewModel.searchShowList(showSearchUiState.queryString)
                            showSearchViewModel.insertShowSearchWord(showSearchUiState.queryString)
                        }
                        showSearchViewModel.onDoneSearch()
                    },
                    onClick = {
                        showSearchViewModel.onDeActiveSearch()
                        showSearchViewModel.setQueryString()
                    },
                    onAction = { showSearchViewModel.setQueryString() }
                )
            } else {
                TopAppBarOnlySearch(
                    containerColor = White,
                    contentColor = Roman_Silver,
                    onClick = { showSearchViewModel.onActiveSearch() }
                )
            }
        }
    ) { paddingValues ->
        if (showSearchUiState.isActiveSearch) {
            ShowSearchContent(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(White),
                queryString = showSearchUiState.queryString,
                isDoneSearch = showSearchUiState.isDoneSearch,
                onSearch = {
                    showSearchViewModel.setQueryString(it)
                    showSearchViewModel.searchShowList(it)
                    showSearchViewModel.insertShowSearchWord(it)
                    showSearchViewModel.onDoneSearch()
                },
                onNavigateDetail = onNavigateDetail
            )
        } else {
            ShowListContent(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(White),
                sortType = showSearchUiState.sortType,
                genre = showSearchUiState.genre,
                onNavigateDetail = onNavigateDetail
            )
        }
    }
}

@Composable
private fun ShowSearchContent(
    modifier: Modifier = Modifier,
    showSearchViewModel: ShowSearchViewModel = hiltViewModel(),
    queryString: String = "",
    isDoneSearch: Boolean = true,
    onSearch: (String) -> Unit = {},
    onNavigateDetail: (String) -> Unit = {}
) {
    val searchWords by showSearchViewModel.searchWords.collectAsStateWithLifecycle()
    val showSearchItems = showSearchViewModel.showSearchItems.collectAsLazyPagingItems()
    LazyColumn(modifier) {
        item {
            Spacer(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Anti_Flash_White)
            )
        }

        if (queryString.isEmpty()) {
            if (searchWords.isEmpty()) {
                item {
                    EmptyContent(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillParentMaxHeight(),
                        text = stringResource(R.string.search_empty_recently_word)
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
                                modifier = Modifier.clickable { showSearchViewModel.deleteShowSearchWordList() },
                                color = Roman_Silver,
                                fontSize = 14.dp.toSp(),
                                fontWeight = FontWeight.Medium,
                                fontFamily = spoqahansanseeo
                            )
                        }
                        searchWords.forEach { searchWord ->
                            SearchTextItem(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp),
                                text = searchWord.word,
                                onDelete = { showSearchViewModel.deleteShowSearchWord(searchWord) },
                                onClick = { onSearch(searchWord.word) }
                            )
                        }
                    }
                }
            }
        } else {
            if (isDoneSearch) {
                itemsIndexed(showSearchItems) { index, showInfoModel ->
                    showInfoModel?.let { model ->
                        PerformanceDetailCard(
                            modifier = Modifier
                                .padding(horizontal = 20.dp)
                                .fillMaxWidth()
                                .padding(vertical = 12.dp),
                            imageUrl = model.poster,
                            painter = painterResource(R.drawable.ic_error_poster),
                            title = model.name,
                            rate = if (model.reviewCount == 0) 0.0f else (model.reviewGradeSum / model.reviewCount.toFloat()),
                            numberOfTotal = model.reviewCount,
                            period = "${model.startDate.toChangeDate()}-${model.endDate.toChangeDate()}",
                            runningTime = if (model.runtime.isEmpty()) "해당 정보 없음" else "${model.runtime.toRunningTime()}분",
                            date = model.showTimes.map {
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
                            location = model.facilityName,
                            onClick = { onNavigateDetail(model.id) },
                            isFavorite = model.favorite,
                            onFavorite = { showSearchViewModel.requestFavoriteShow(model.id) },
                            onDisFavorite = { showSearchViewModel.deleteFavoriteShow(model.id) }
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
private fun ShowListContent(
    modifier: Modifier = Modifier,
    showSearchViewModel: ShowSearchViewModel = hiltViewModel(),
    sortType: ShowSortType = ShowSortType.REVIEW_GRADE,
    genre: ShowGenreType = ShowGenreType.PLAY,
    onNavigateDetail: (String) -> Unit
) {
    var isShowDialog by remember { mutableStateOf(false) }
    val performanceUiState by showSearchViewModel.uiState.collectAsStateWithLifecycle()
    val showItems = showSearchViewModel.showItems.collectAsLazyPagingItems()

    if (isShowDialog) {
        SelectSortTypeBottomSheet(
            sortType = sortType,
            onSelectSortType = {
                showSearchViewModel.changeSortType(it)
                isShowDialog = false
            },
            onDismissRequest = { isShowDialog = false }
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
                    isCheckFirstType = genre == ShowGenreType.PLAY,
                    onTypeChange = { check ->
                        showSearchViewModel.changeGenre(if (check) ShowGenreType.PLAY else ShowGenreType.MUSICAL)
                    }
                )
                SortTypeRow(
                    modifier = Modifier.padding(top = 28.dp),
                    sortType = sortType,
                    onClick = { isShowDialog = true }
                )
            }
        }

        itemsIndexed(showItems) { index, showInfoModel ->
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
                        onNavigateDetail(showInfoModel.id)
                    },
                    isFavorite = showInfoModel.favorite,
                    onFavorite = { showSearchViewModel.requestFavoriteShow(showInfoModel.id) },
                    onDisFavorite = { showSearchViewModel.deleteFavoriteShow(showInfoModel.id) }
                )
                if (index != showItems.itemCount) {
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
