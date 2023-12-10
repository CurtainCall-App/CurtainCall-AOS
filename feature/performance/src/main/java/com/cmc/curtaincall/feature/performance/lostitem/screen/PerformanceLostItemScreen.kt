package com.cmc.curtaincall.feature.performance.lostitem.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.component.basic.CurtainCallDropDownButton
import com.cmc.curtaincall.common.designsystem.component.basic.SearchAppBar
import com.cmc.curtaincall.common.designsystem.component.basic.SearchTopAppBarWithBack
import com.cmc.curtaincall.common.designsystem.component.custom.SelectedDateCalender
import com.cmc.curtaincall.common.designsystem.component.items.EmptyItem
import com.cmc.curtaincall.common.designsystem.component.items.GridLostItem
import com.cmc.curtaincall.common.designsystem.component.items.SearchItem
import com.cmc.curtaincall.common.designsystem.component.show.lostproperty.LostPropertyTypeGrid
import com.cmc.curtaincall.common.designsystem.component.show.lostproperty.LostPropertyTypeItem
import com.cmc.curtaincall.common.designsystem.extensions.toSp
import com.cmc.curtaincall.common.designsystem.theme.*
import com.cmc.curtaincall.feature.performance.detail.PerformanceDetailViewModel
import com.cmc.curtaincall.feature.performance.lostitem.PerformanceLostItemViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PerformanceLostItemScreen(
    performanceLostItemViewModel: PerformanceLostItemViewModel = hiltViewModel(),
    performanceDetailViewModel: PerformanceDetailViewModel,
    facilityName: String?,
    onNavigateLostItemDetail: (Int) -> Unit,
    onNavigateLostItemCreate: (String, String) -> Unit,
    onBack: () -> Unit
) {
    checkNotNull(facilityName)
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(White)

    val performanceLostItemUiState by performanceLostItemViewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            if (performanceLostItemUiState.isActiveSearch) {
                SearchAppBar(
                    value = performanceLostItemUiState.queryString,
                    onValueChange = {
                        performanceLostItemViewModel.setQueryString(it)
                        performanceLostItemViewModel.changeDoneSearch(false)
                    },
                    containerColor = White,
                    contentColor = Nero,
                    placeholder = stringResource(R.string.search_lostitem_title),
                    onDone = {
                        if (performanceLostItemUiState.queryString.isNotEmpty()) {
                            performanceLostItemViewModel.searchLostItemList(
                                performanceDetailViewModel.uiState.value.facilityDetailModel.id,
                                performanceLostItemUiState.queryString
                            )
                            performanceLostItemViewModel.insertLostItemSearchWord()
                        }
                        performanceLostItemViewModel.changeDoneSearch(true)
                    },
                    onClick = {
                        performanceLostItemViewModel.changeActiveSearch(false)
                        performanceLostItemViewModel.setQueryString("")
                    },
                    onAction = { performanceLostItemViewModel.setQueryString("") }
                )
            } else {
                SearchTopAppBarWithBack(
                    title = stringResource(R.string.performance_find_lost_item),
                    containerColor = White,
                    contentColor = Nero,
                    tint = Roman_Silver,
                    onBack = onBack,
                    onClick = { performanceLostItemViewModel.changeActiveSearch(true) }
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onNavigateLostItemCreate(performanceDetailViewModel.uiState.value.facilityDetailModel.id, facilityName)
                },
                modifier = Modifier
                    .padding(bottom = 40.dp)
                    .size(58.dp),
                shape = CircleShape,
                containerColor = Cetacean_Blue
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_pen),
                    contentDescription = null,
                    modifier = Modifier.size(29.dp),
                    tint = Color.Unspecified
                )
            }
        }
    ) { paddingValues ->
        if (performanceLostItemUiState.isActiveSearch) {
            PerformanceLostItemSearchContent(
                performanceLostItemViewModel = performanceLostItemViewModel,
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(White),
                onClick = {
                    performanceLostItemViewModel.setQueryString(it)
                    performanceLostItemViewModel.searchLostItemList(
                        performanceDetailViewModel.uiState.value.facilityDetailModel.id,
                        it
                    )
                    performanceLostItemViewModel.insertLostItemSearchWord()
                    performanceLostItemViewModel.changeDoneSearch(true)
                },
                onNavigateLostItemDetail = onNavigateLostItemDetail
            )
        } else {
            PerformanceLostItemContent(
                performanceLostItemViewModel = performanceLostItemViewModel,
                performanceDetailViewModel = performanceDetailViewModel,
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(White),
                facilityName = facilityName,
                onNavigateLostItemDetail = onNavigateLostItemDetail
            )
        }
    }
}

@Composable
private fun PerformanceLostItemSearchContent(
    performanceLostItemViewModel: PerformanceLostItemViewModel,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
    onNavigateLostItemDetail: (Int) -> Unit
) {
    val searchWords by performanceLostItemViewModel.searchWords.collectAsStateWithLifecycle()
    val performanceLostItemUiState by performanceLostItemViewModel.uiState.collectAsStateWithLifecycle()
    val lostItems = performanceLostItemUiState.lostItemSearchItems.collectAsLazyPagingItems()
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.padding(bottom = 18.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item(span = { GridItemSpan(2) }) {
            Spacer(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Cultured)
            )
        }

        if (performanceLostItemUiState.queryString.isEmpty()) {
            if (searchWords.isEmpty()) {
                item(span = { GridItemSpan(2) }) {
                    EmptyItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 232.dp),
                        alert = stringResource(R.string.search_empty_recently_word)
                    )
                }
            } else {
                item(span = { GridItemSpan(2) }) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .padding(top = 2.dp)
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
                                modifier = Modifier.clickable { performanceLostItemViewModel.deleteLostItemSearchWordList() },
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
                                onDelete = { performanceLostItemViewModel.deleteLostItemSearchWord(searchWord) }
                            )
                        }
                    }
                }
            }
        } else {
            if (performanceLostItemUiState.isDoneSearch) {
                items(lostItems.itemCount) { index ->
                    lostItems[index]?.let { lostItem ->
                        GridLostItem(
                            modifier = Modifier
                                .padding(
                                    start = if (index % 2 == 0) 20.dp else 0.dp,
                                    end = if (index % 2 == 0) 0.dp else 20.dp
                                )
                                .clickable {
                                    performanceLostItemViewModel.requestLostDetailItem(lostItem.id)
                                    onNavigateLostItemDetail(lostItem.id)
                                }
                                .background(Cultured, RoundedCornerShape(10.dp))
                                .padding(horizontal = 8.dp)
                                .padding(top = 8.dp, bottom = 15.dp),
                            imageUrl = lostItem.imageUrl,
                            title = lostItem.title,
                            location = lostItem.facilityName,
                            date = lostItem.foundDate
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun PerformanceLostItemContent(
    performanceLostItemViewModel: PerformanceLostItemViewModel,
    performanceDetailViewModel: PerformanceDetailViewModel,
    modifier: Modifier = Modifier,
    facilityName: String,
    onNavigateLostItemDetail: (Int) -> Unit
) {
    val lostItems = performanceDetailViewModel.lostItems.collectAsLazyPagingItems()
    var isClickedDate by remember { mutableStateOf(false) }
    var isClickedType by remember { mutableStateOf(false) }
    var lostDateState by remember { mutableStateOf("") }
    var lostTypeState by remember { mutableStateOf("") }

    Box(modifier.padding(vertical = 23.dp, horizontal = 20.dp)) {
        PerformanceLostItemHeader(
            modifier = Modifier
                .zIndex(if (isClickedDate or isClickedType) 1f else 0f)
                .fillMaxWidth(),
            location = facilityName,
            lostDate = lostDateState,
            lostType = lostTypeState,
            isClickedDate = isClickedDate,
            isClickedType = isClickedType,
            onClickDate = { isClickedDate = it },
            onClickType = { isClickedType = it },
            content = {
                if (isClickedDate) {
                    SelectedDateCalender(
                        modifier = Modifier.padding(top = 10.dp),
                        onDateClick = {
                            lostDateState = String.format(
                                "%d.%d.%d",
                                it.date.year,
                                it.date.month.value,
                                it.date.dayOfMonth
                            )
                            isClickedDate = false
                            performanceDetailViewModel.requestLostItemList(
                                foundDate = String.format(
                                    "%d-%02d-%02d",
                                    it.date.year,
                                    it.date.month.value,
                                    it.date.dayOfMonth
                                )
                            )
                            lostItems.refresh()
                        }
                    )
                }
                if (isClickedType) {
                    LostPropertyTypeGrid(
                        modifier = Modifier
                            .padding(vertical = 24.dp, horizontal = 30.dp)
                            .fillMaxWidth()
                            .height(482.dp),
                        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 23.dp),
                        verticalArrangement = Arrangement.spacedBy(28.dp),
                        horizontalArrangement = Arrangement.spacedBy(49.dp)
                    ) {
                        LostPropertyTypeItem(
                            modifier = Modifier.size(60.dp, 90.dp),
                            lostPropertyType = it,
                            onTypeChange = { lostPropertyType ->
                                lostTypeState = lostPropertyType.label
                                isClickedType = false
                                performanceDetailViewModel.requestLostItemList(type = lostPropertyType.code)
                                lostItems.refresh()
                            }
                        )
                    }
//                    LostItemTypeGrid(
//                        modifier = Modifier
//                            .padding(vertical = 24.dp, horizontal = 30.dp)
//                            .fillMaxWidth()
//                            .height(336.dp),
//                        itemModifier = Modifier.size(60.dp, 90.dp),
//                        onTypeChange = {
//                            lostTypeState = it.label
//                            isClickedType = false
//                            performanceDetailViewModel.requestLostItemList(type = it.code)
//                            lostItems.refresh()
//                        }
//                    )
                }
            }
        )
        if (lostItems.itemCount == 0) {
            Box(
                modifier = Modifier
                    .padding(top = 88.dp)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                EmptyItem(
                    alert = stringResource(R.string.performance_lostitem_empty)
                )
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(top = 108.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(lostItems.itemCount) { index ->
                    lostItems[index]?.let { lostItem ->
                        GridLostItem(
                            modifier = Modifier
                                .clickable {
                                    performanceLostItemViewModel.requestLostDetailItem(lostItem.id)
                                    onNavigateLostItemDetail(lostItem.id)
                                }
                                .background(Cultured, RoundedCornerShape(10.dp))
                                .padding(horizontal = 8.dp)
                                .padding(top = 8.dp, bottom = 15.dp),
                            imageUrl = lostItem.imageUrl,
                            title = lostItem.title,
                            location = lostItem.facilityName,
                            date = lostItem.foundDate
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun PerformanceLostItemHeader(
    modifier: Modifier = Modifier,
    location: String,
    lostDate: String,
    lostType: String,
    isClickedDate: Boolean = false,
    isClickedType: Boolean = false,
    onClickDate: (Boolean) -> Unit = {},
    onClickType: (Boolean) -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    Column(modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Me_Pink.copy(0.2f), RoundedCornerShape(6.dp))
                .padding(vertical = 9.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = location,
                color = Me_Pink,
                fontSize = 16.dp.toSp(),
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
        ) {
            CurtainCallDropDownButton(
                modifier = Modifier
                    .padding(end = 6.dp)
                    .weight(1f)
                    .height(38.dp)
                    .clickable {
                        onClickDate(isClickedDate.not())
                        if (isClickedDate.not()) onClickType(false)
                    },
                isClicked = isClickedDate,
                title = lostDate.ifEmpty { stringResource(R.string.performance_find_lost_item_date) },
                fontSize = 14.dp.toSp(),
                containerColor = Cultured,
                contentColor = if (lostDate.isEmpty()) Silver_Sand else Nero,
                borderColor = Roman_Silver,
                radiusSize = 6.dp,
                contentModifier = Modifier
                    .padding(vertical = 9.dp)
                    .padding(start = 12.dp, end = 8.dp)
            )
            CurtainCallDropDownButton(
                modifier = Modifier
                    .padding(start = 6.dp)
                    .weight(1f)
                    .height(38.dp)
                    .clickable {
                        onClickType(isClickedType.not())
                        if (isClickedType.not()) onClickDate(false)
                    },
                isClicked = isClickedType,
                title = lostType.ifEmpty { stringResource(R.string.performance_find_lost_item_type) },
                fontSize = 14.dp.toSp(),
                containerColor = Cultured,
                contentColor = if (lostType.isEmpty()) Silver_Sand else Nero,
                borderColor = Roman_Silver,
                radiusSize = 6.dp,
                contentModifier = Modifier
                    .padding(vertical = 9.dp)
                    .padding(start = 12.dp, end = 8.dp)
            )
        }
        if (isClickedDate or isClickedType) {
            content()
        }
    }
}
