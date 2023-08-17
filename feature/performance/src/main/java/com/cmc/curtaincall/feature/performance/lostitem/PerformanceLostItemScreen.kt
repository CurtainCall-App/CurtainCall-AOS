package com.cmc.curtaincall.feature.performance.lostitem

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.basic.CurtainCallDropDownButton
import com.cmc.curtaincall.common.design.component.basic.SearchAppBar
import com.cmc.curtaincall.common.design.component.basic.SearchTopAppBarWithBack
import com.cmc.curtaincall.common.design.component.custom.SelectedDateCalender
import com.cmc.curtaincall.common.design.component.items.GridLostItem
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PerformanceLostItemScreen(
    onNavigateLostItemDetail: () -> Unit,
    onNavigateLostItemCreate: () -> Unit,
    onBack: () -> Unit
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
                    placeholder = stringResource(R.string.search_lostitem_title),
                    onClick = { isActiveSearchState = false }
                )
            } else {
                SearchTopAppBarWithBack(
                    title = stringResource(R.string.performance_find_lost_item),
                    containerColor = White,
                    contentColor = Nero,
                    tint = Roman_Silver,
                    onBack = onBack,
                    onClick = { isActiveSearchState = true }
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onNavigateLostItemCreate() },
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
        if (isActiveSearchState) {
            // TODO
        } else {
            PerformanceLostItemContent(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(White),
                onNavigateLostItemDetail = onNavigateLostItemDetail
            )
        }
    }
}

@Composable
private fun PerformanceLostItemContent(
    modifier: Modifier = Modifier,
    onNavigateLostItemDetail: () -> Unit
) {
    var isClickedDate by remember { mutableStateOf(false) }
    var isClickedType by remember { mutableStateOf(false) }
    var lostDateState by remember { mutableStateOf("") }
    var lostTypeState by remember { mutableStateOf("") }
    Box(
        modifier = modifier
            .padding(top = 23.dp)
            .padding(horizontal = 20.dp)
    ) {
        PerformanceLostItemHeader(
            modifier = Modifier
                .zIndex(if (isClickedDate or isClickedType) 1f else 0f)
                .fillMaxWidth(),
            location = "LG아트센터 서울",
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
                        }
                    )
                }
                if (isClickedType) {
                    LostItemTypeGrid(
                        modifier = Modifier
                            .padding(vertical = 24.dp, horizontal = 30.dp)
                            .fillMaxWidth()
                            .height(336.dp),
                        itemModifier = Modifier.size(60.dp, 90.dp),
                        onTypeChange = {
                            lostTypeState = it.label
                            isClickedType = false
                        }
                    )
                }
            }
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(top = 108.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            itemsIndexed(List(9) { it }) { index, item ->
                GridLostItem(
                    modifier = Modifier
                        .clickable { onNavigateLostItemDetail() }
                        .background(Cultured, RoundedCornerShape(10.dp))
                        .padding(horizontal = 8.dp)
                        .padding(top = 8.dp, bottom = 15.dp),
                    painter = painterResource(R.drawable.img_poster),
                    title = "아이폰 핑크",
                    location = "LG 아트센터 서울",
                    date = "2023.7.15"
                )
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
                fontFamily = spoqahansanseeo
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
