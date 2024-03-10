package com.cmc.curtaincall.feature.show.lostproperty

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.component.appbars.CurtainCallSearchTitleTopAppBarWithBack
import com.cmc.curtaincall.common.designsystem.component.basic.SystemUiStatusBar
import com.cmc.curtaincall.common.designsystem.component.buttons.common.CurtainCallFilledButton
import com.cmc.curtaincall.common.designsystem.component.chips.CurtainCallCalenderSelectShip
import com.cmc.curtaincall.common.designsystem.custom.show.LostPropertyContent
import com.cmc.curtaincall.common.designsystem.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ShowLostPropertyScreen(
    showLostPropertyViewModel: ShowLostPropertyViewModel = hiltViewModel(),
    facilityId: String? = null,
    facilityName: String? = null,
    onNavigateToLostPropertyDetail: (Int, Boolean) -> Unit = { _, _ -> },
    onNavigateToLostPropertyCreate: (String, String) -> Unit = { _, _ -> },
    onBack: () -> Unit = {}
) {
    requireNotNull(facilityId)
    requireNotNull(facilityName)

    LaunchedEffect(Unit) {
        showLostPropertyViewModel.fetchLostPropertyList(facilityId = facilityId)
    }

    val searchAppBarState by showLostPropertyViewModel.searchAppBarState.collectAsStateWithLifecycle()
    SystemUiStatusBar(White)
    Scaffold(
        topBar = {
            CurtainCallSearchTitleTopAppBarWithBack(
                title = stringResource(R.string.lost_property),
                searchAppBarState = searchAppBarState,
                onBack = onBack
            )
        },
        floatingActionButton = {
            CurtainCallFilledButton(
                text = stringResource(R.string.write_lost_property),
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 30.dp)
                    .fillMaxWidth()
                    .height(52.dp),
                textStyle = CurtainCallTheme.typography.body2.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                onClick = { onNavigateToLostPropertyCreate(facilityId, facilityName) }
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        if (searchAppBarState.isSearchMode.value) {
            ShowLostPropertyListContent(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(CurtainCallTheme.colors.background),
                facilityName = facilityName
            )
        } else {
            ShowLostPropertyListContent(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(CurtainCallTheme.colors.background),
                facilityName = facilityName
            )
        }
    }
}

@Composable
private fun ShowLostPropertyListContent(
    modifier: Modifier = Modifier,
    showLostPropertyViewModel: ShowLostPropertyViewModel = hiltViewModel(),
    facilityName: String
) {
    val lostPropertyModels = showLostPropertyViewModel.lostPropertyModels.collectAsLazyPagingItems()
    Column(modifier) {
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = facilityName,
                style = CurtainCallTheme.typography.subTitle4
            )
            Spacer(Modifier.weight(1f))
            CurtainCallCalenderSelectShip()
        }
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(20.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(lostPropertyModels.itemCount) { index ->
                lostPropertyModels[index]?.let { lostPropertyModel ->
                    LostPropertyContent(
                        modifier = Modifier.size(154.dp, 195.dp),
                        lostPropertyModel = lostPropertyModel,
                        onClick = {}
                    )
                }
            }
        }
    }
}

// @Composable
// private fun ShowLostPropertySearchContent(
//    modifier: Modifier = Modifier,
//    showLostPropertyViewModel: ShowLostPropertyViewModel = hiltViewModel(),
//    onClick: (String) -> Unit,
//    onNavigateLostPropertyDetail: (Int, Boolean) -> Unit
// ) {
//    val searchWords by showLostPropertyViewModel.searchWords.collectAsStateWithLifecycle()
//    val showLostPropertyUiState by showLostPropertyViewModel.uiState.collectAsStateWithLifecycle()
//    val lostPropertyItems = showLostPropertyViewModel.lostPropertySearchItems.collectAsLazyPagingItems()
//
//    LazyVerticalGrid(
//        columns = GridCells.Fixed(2),
//        modifier = modifier.padding(bottom = 18.dp),
//        verticalArrangement = Arrangement.spacedBy(18.dp),
//        horizontalArrangement = Arrangement.spacedBy(12.dp)
//    ) {
//        item(span = { GridItemSpan(2) }) {
//            Spacer(
//                modifier = Modifier
//                    .padding(top = 20.dp)
//                    .fillMaxWidth()
//                    .height(1.dp)
//                    .background(Cultured)
//            )
//        }
//
//        if (showLostPropertyUiState.queryString.isEmpty()) {
//            if (searchWords.isEmpty()) {
//                item(span = { GridItemSpan(2) }) {
//                    EmptyContent(
//                        modifier = Modifier.fillMaxSize(),
//                        text = stringResource(R.string.search_empty_recently_word)
//                    )
//                }
//            } else {
//                item(span = { GridItemSpan(2) }) {
//                    Column(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(horizontal = 20.dp)
//                            .padding(top = 2.dp)
//                    ) {
//                        Row(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(bottom = 10.dp),
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//                            Text(
//                                text = stringResource(R.string.search_recently_word),
//                                modifier = Modifier.weight(1f),
//                                color = Chinese_Black,
//                                fontSize = 16.dp.toSp(),
//                                fontWeight = FontWeight.Bold,
//                                fontFamily = spoqahansanseeo
//                            )
//                            Text(
//                                text = stringResource(R.string.search_delete_recentyl_word),
//                                modifier = Modifier.clickable { showLostPropertyViewModel.deleteLostPropertySearchWordList() },
//                                color = Roman_Silver,
//                                fontSize = 14.dp.toSp(),
//                                fontWeight = FontWeight.Medium,
//                                fontFamily = spoqahansanseeo
//                            )
//                        }
//                        searchWords.forEach { searchWord ->
//                            SearchTextItem(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(vertical = 10.dp),
//                                text = searchWord.word,
//                                onDelete = { showLostPropertyViewModel.deleteLostPropertySearchWord(searchWord) },
//                                onClick = { onClick(searchWord.word) }
//                            )
//                        }
//                    }
//                }
//            }
//        } else {
//            if (showLostPropertyUiState.isDoneSearch) {
//                items(lostPropertyItems.itemCount) { index ->
//                    lostPropertyItems[index]?.let { lostItem ->
//                        GridLostItem(
//                            modifier = Modifier
//                                .padding(
//                                    start = if (index % 2 == 0) 20.dp else 0.dp,
//                                    end = if (index % 2 == 0) 0.dp else 20.dp
//                                )
//                                .clickable {
//                                    showLostPropertyViewModel.requestLostDetailProperty(lostItem.id)
//                                    onNavigateLostPropertyDetail(lostItem.id, false)
//                                }
//                                .background(Cultured, RoundedCornerShape(10.dp))
//                                .padding(horizontal = 8.dp)
//                                .padding(top = 8.dp, bottom = 15.dp),
//                            imageUrl = lostItem.imageUrl,
//                            title = lostItem.title,
//                            location = lostItem.facilityName,
//                            date = lostItem.foundDate
//                        )
//                    }
//                }
//            }
//        }
//    }
// }

// @Composable
// private fun ShowLostPropertyContent(
//    modifier: Modifier = Modifier,
//    showLostPropertyViewModel: ShowLostPropertyViewModel = hiltViewModel(),
//    facilityId: String,
//    facilityName: String,
//    onNavigateLostPropertyDetail: (Int, Boolean) -> Unit
// ) {
//    val lostPropertyItems = showLostPropertyViewModel.lostPropertySearchItems.collectAsLazyPagingItems()
//    var isClickedDate by remember { mutableStateOf(false) }
//    var isClickedType by remember { mutableStateOf(false) }
//    var lostDateState by remember { mutableStateOf("") }
//    var lostTypeState by remember { mutableStateOf("") }
//
//    Box(modifier.padding(vertical = 23.dp, horizontal = 20.dp)) {
//        ShowLostPropertyHeader(
//            modifier = Modifier
//                .zIndex(if (isClickedDate or isClickedType) 1f else 0f)
//                .fillMaxWidth(),
//            location = facilityName,
//            lostDate = lostDateState,
//            lostType = lostTypeState,
//            isClickedDate = isClickedDate,
//            isClickedType = isClickedType,
//            onClickDate = { isClickedDate = it },
//            onClickType = { isClickedType = it },
//            content = {
//                if (isClickedDate) {
//                    SelectedDateCalender(
//                        modifier = Modifier.padding(top = 10.dp),
//                        onDateClick = {
//                            lostDateState = String.format(
//                                "%d.%d.%d",
//                                it.date.year,
//                                it.date.month.value,
//                                it.date.dayOfMonth
//                            )
//                            isClickedDate = false
//                            showLostPropertyViewModel.requestLostPropertyList(
//                                facilityId = facilityId,
//                                foundData = String.format(
//                                    "%d-%02d-%02d",
//                                    it.date.year,
//                                    it.date.month.value,
//                                    it.date.dayOfMonth
//                                )
//                            )
//                            lostPropertyItems.refresh()
//                        }
//                    )
//                }
//                if (isClickedType) {
//                    LostPropertyTypeGrid(
//                        modifier = Modifier
//                            .padding(vertical = 24.dp, horizontal = 30.dp)
//                            .fillMaxWidth()
//                            .height(482.dp),
//                        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 23.dp),
//                        verticalArrangement = Arrangement.spacedBy(28.dp),
//                        horizontalArrangement = Arrangement.spacedBy(49.dp)
//                    ) {
//                        LostPropertyTypeItem(
//                            modifier = Modifier.size(60.dp, 90.dp),
//                            lostPropertyType = it,
//                            onTypeChange = { lostPropertyType ->
//                                lostTypeState = lostPropertyType.label
//                                isClickedType = false
//                                showLostPropertyViewModel.requestLostPropertyList(facilityId = facilityId, type = lostPropertyType.code)
//                                lostPropertyItems.refresh()
//                            }
//                        )
//                    }
//                }
//            }
//        )
//        if (lostPropertyItems.itemCount == 0) {
//            EmptyContent(
//                modifier = Modifier.fillMaxSize(),
//                text = stringResource(R.string.performance_lostitem_empty)
//            )
//        } else {
//            LazyVerticalGrid(
//                columns = GridCells.Fixed(2),
//                modifier = Modifier.padding(top = 108.dp),
//                verticalArrangement = Arrangement.spacedBy(18.dp),
//                horizontalArrangement = Arrangement.spacedBy(12.dp)
//            ) {
//                items(lostPropertyItems.itemCount) { index ->
//                    lostPropertyItems[index]?.let { lostItem ->
//                        GridLostItem(
//                            modifier = Modifier
//                                .clickable {
//                                    showLostPropertyViewModel.requestLostDetailProperty(lostItem.id)
//                                    onNavigateLostPropertyDetail(lostItem.id, false)
//                                }
//                                .background(Cultured, RoundedCornerShape(10.dp))
//                                .padding(horizontal = 8.dp)
//                                .padding(top = 8.dp, bottom = 15.dp),
//                            imageUrl = lostItem.imageUrl,
//                            title = lostItem.title,
//                            location = lostItem.facilityName,
//                            date = lostItem.foundDate
//                        )
//                    }
//                }
//            }
//        }
//    }
// }
//
// @Composable
// private fun ShowLostPropertyHeader(
//    modifier: Modifier = Modifier,
//    location: String,
//    lostDate: String,
//    lostType: String,
//    isClickedDate: Boolean = false,
//    isClickedType: Boolean = false,
//    onClickDate: (Boolean) -> Unit = {},
//    onClickType: (Boolean) -> Unit = {},
//    content: @Composable () -> Unit = {}
// ) {
//    Column(modifier) {
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(Me_Pink.copy(0.2f), RoundedCornerShape(6.dp))
//                .padding(vertical = 9.dp),
//            contentAlignment = Alignment.Center
//        ) {
//            Text(
//                text = location,
//                color = Me_Pink,
//                fontSize = 16.dp.toSp(),
//                fontWeight = FontWeight.Bold,
//                fontFamily = spoqahansanseeo,
//                maxLines = 1,
//                overflow = TextOverflow.Ellipsis
//            )
//        }
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 12.dp)
//        ) {
//            CurtainCallDropDownButton(
//                modifier = Modifier
//                    .padding(end = 6.dp)
//                    .weight(1f)
//                    .height(38.dp)
//                    .clickable {
//                        onClickDate(isClickedDate.not())
//                        if (isClickedDate.not()) onClickType(false)
//                    },
//                isClicked = isClickedDate,
//                title = lostDate.ifEmpty { stringResource(R.string.performance_find_lost_item_date) },
//                fontSize = 14.dp.toSp(),
//                containerColor = Cultured,
//                contentColor = if (lostDate.isEmpty()) Silver_Sand else Nero,
//                borderColor = Roman_Silver,
//                radiusSize = 6.dp,
//                contentModifier = Modifier
//                    .padding(vertical = 9.dp)
//                    .padding(start = 12.dp, end = 8.dp)
//            )
//            CurtainCallDropDownButton(
//                modifier = Modifier
//                    .padding(start = 6.dp)
//                    .weight(1f)
//                    .height(38.dp)
//                    .clickable {
//                        onClickType(isClickedType.not())
//                        if (isClickedType.not()) onClickDate(false)
//                    },
//                isClicked = isClickedType,
//                title = lostType.ifEmpty { stringResource(R.string.performance_find_lost_item_type) },
//                fontSize = 14.dp.toSp(),
//                containerColor = Cultured,
//                contentColor = if (lostType.isEmpty()) Silver_Sand else Nero,
//                borderColor = Roman_Silver,
//                radiusSize = 6.dp,
//                contentModifier = Modifier
//                    .padding(vertical = 9.dp)
//                    .padding(start = 12.dp, end = 8.dp)
//            )
//        }
//        if (isClickedDate or isClickedType) {
//            content()
//        }
//    }
// }
