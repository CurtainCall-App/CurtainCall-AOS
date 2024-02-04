package com.cmc.curtaincall.feature.show.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.component.appbars.CurtainCallSearchTitleTopAppBar
import com.cmc.curtaincall.common.designsystem.component.appbars.rememberSearchAppBarType
import com.cmc.curtaincall.common.designsystem.component.basic.SystemUiStatusBar
import com.cmc.curtaincall.common.designsystem.component.canvas.CurtainCallCoachMark
import com.cmc.curtaincall.common.designsystem.component.card.PerformanceDetailCard
import com.cmc.curtaincall.common.designsystem.component.chips.CurtainCallBasicChip
import com.cmc.curtaincall.common.designsystem.component.content.empty.EmptyContent
import com.cmc.curtaincall.common.designsystem.component.custom.SelectSortTypeBottomSheet
import com.cmc.curtaincall.common.designsystem.component.item.search.SearchTextItem
import com.cmc.curtaincall.common.designsystem.custom.poster.CurtainCallShowPoster
import com.cmc.curtaincall.common.designsystem.dimension.Paddings
import com.cmc.curtaincall.common.designsystem.extensions.toSp
import com.cmc.curtaincall.common.designsystem.theme.*
import com.cmc.curtaincall.common.utility.extensions.ShowDay
import com.cmc.curtaincall.common.utility.extensions.toChangeDate
import com.cmc.curtaincall.common.utility.extensions.toRunningTime
import com.cmc.curtaincall.domain.type.ShowGenreType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowListScreen(
    showListViewModel: ShowListViewModel = hiltViewModel(),
    onNavigateDetail: (String) -> Unit
) {
    val showSearchUiState by showListViewModel.uiState.collectAsStateWithLifecycle()
    val searchAppBarType = rememberSearchAppBarType()
    SystemUiStatusBar(White)
    Scaffold(
        topBar = {
            CurtainCallSearchTitleTopAppBar(
                title = stringResource(R.string.show),
                searchAppBarType = searchAppBarType
            )
//            if (showSearchUiState.isActiveSearch) {
//                SearchAppBar(
//                    value = showSearchUiState.queryString,
//                    onValueChange = {
//                        showSearchViewModel.setQueryString(it)
//                        showSearchViewModel.onSearching()
//                    },
//                    containerColor = White,
//                    contentColor = Nero,
//                    placeholder = stringResource(R.string.search_performance_title),
//                    onDone = {
//                        if (showSearchUiState.queryString.trim().isNotEmpty()) {
//                            showSearchViewModel.searchShowList(showSearchUiState.queryString)
//                            showSearchViewModel.insertShowSearchWord(showSearchUiState.queryString)
//                        }
//                        showSearchViewModel.onDoneSearch()
//                    },
//                    onClick = {
//                        showSearchViewModel.onDeActiveSearch()
//                        showSearchViewModel.setQueryString()
//                    },
//                    onAction = { showSearchViewModel.setQueryString() }
//                )
//            } else {
//                TopAppBarOnlySearch(
//                    containerColor = White,
//                    contentColor = Roman_Silver,
//                    onClick = { showSearchViewModel.onActiveSearch() }
//                )
//            }
        }
    ) { paddingValues ->
        if (searchAppBarType.isSearchMode.value) {
            ShowSearchContent(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(White),
                queryString = showSearchUiState.queryString,
                isDoneSearch = showSearchUiState.isDoneSearch,
                onSearch = {
                    showListViewModel.setQueryString(it)
                    showListViewModel.searchShowList(it)
                    showListViewModel.insertShowSearchWord(it)
                    showListViewModel.onDoneSearch()
                },
                onNavigateDetail = onNavigateDetail
            )
        } else {
            ShowListContent(
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
private fun ShowSearchContent(
    modifier: Modifier = Modifier,
    showListViewModel: ShowListViewModel = hiltViewModel(),
    queryString: String = "",
    isDoneSearch: Boolean = true,
    onSearch: (String) -> Unit = {},
    onNavigateDetail: (String) -> Unit = {}
) {
    val searchWords by showListViewModel.searchWords.collectAsStateWithLifecycle()
    val showSearchItems = showListViewModel.showSearchItems.collectAsLazyPagingItems()
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
                                modifier = Modifier.clickable { showListViewModel.deleteShowSearchWordList() },
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
                                onDelete = { showListViewModel.deleteShowSearchWord(searchWord) },
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
                            onFavorite = { showListViewModel.requestFavoriteShow(model.id) },
                            onDisFavorite = { showListViewModel.deleteFavoriteShow(model.id) }
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
    showListViewModel: ShowListViewModel = hiltViewModel(),
    onNavigateDetail: (String) -> Unit
) {
    val sortType by showListViewModel.sortType.collectAsStateWithLifecycle()
    val genreType by showListViewModel.genreType.collectAsStateWithLifecycle()
    val showInfoModels = showListViewModel.showInfoModels.collectAsLazyPagingItems()
    val isFirstEntry by showListViewModel.isFirstEntry.collectAsStateWithLifecycle()

    LaunchedEffect(showListViewModel) {
        showListViewModel.isRefresh.collect { isRefresh ->
            if (isRefresh) showInfoModels.refresh()
        }
    }

    var isShowDialog by remember { mutableStateOf(false) }
    val performanceUiState by showListViewModel.uiState.collectAsStateWithLifecycle()

    if (isShowDialog) {
        SelectSortTypeBottomSheet(
            sortType = sortType,
            onSelectSortType = {
                showListViewModel.changeSortType(it)
                isShowDialog = false
            },
            onDismissRequest = { isShowDialog = false }
        )
    }

    Box(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .fillMaxSize()
    ) {
        Column {
            Row(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CurtainCallBasicChip(
                    text = ShowGenreType.PLAY.value,
                    textStyle = CurtainCallTheme.typography.body2,
                    isSelect = genreType == ShowGenreType.PLAY,
                    onClick = { showListViewModel.selectGenreType(ShowGenreType.PLAY) }
                )
                CurtainCallBasicChip(
                    modifier = Modifier.padding(start = Paddings.medium),
                    text = ShowGenreType.MUSICAL.value,
                    textStyle = CurtainCallTheme.typography.body2,
                    isSelect = genreType == ShowGenreType.MUSICAL,
                    onClick = { showListViewModel.selectGenreType(ShowGenreType.MUSICAL) }
                )
                Spacer(Modifier.weight(1f))
                Row(
                    modifier = Modifier.clickable { },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = sortType.value,
                        style = CurtainCallTheme.typography.body3
                    )
                    Icon(
                        painter = painterResource(R.drawable.ic_down),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 2.dp)
                            .size(12.dp),
                        tint = Color.Unspecified
                    )
                }
            }
        }
        if (isFirstEntry) {
            CurtainCallCoachMark(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 51.dp)
                    .zIndex(1f),
                text = stringResource(R.string.show_coach_mark),
                onClick = { showListViewModel.setFirstEntry() }
            )
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(top = 67.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(26.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(showInfoModels.itemCount) { index ->
                showInfoModels[index]?.let { showItem ->
                    CurtainCallShowPoster(
                        model = showItem.poster,
                        text = showItem.name,
                        isLike = showItem.favorite,
                        onLikeClick = {
                            showListViewModel.checkShowLike(
                                showId = showItem.id,
                                isLike = !showItem.favorite
                            )
                        },
                        onClick = { onNavigateDetail(showItem.id) }
                    )
                }
            }
        }
    }
}
