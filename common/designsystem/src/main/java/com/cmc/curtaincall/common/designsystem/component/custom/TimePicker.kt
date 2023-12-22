package com.cmc.curtaincall.common.designsystem.component.custom

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.extensions.toSp
import com.cmc.curtaincall.common.designsystem.theme.*
import kotlinx.coroutines.flow.distinctUntilChanged
import java.time.LocalDateTime

enum class Meridiem {
    AM, PM
}

data class TimeModel(
    val hour: Int,
    val minute: Int,
    val meridiem: Meridiem
)

private fun getMeridiem(hour: Int) = if (hour >= 12) Meridiem.PM else Meridiem.AM

@Composable
fun CurtainCallTimePicker(
    modifier: Modifier = Modifier,
    onClick: (TimeModel) -> Unit
) {
    var hourState by remember { mutableStateOf(LocalDateTime.now().hour) }
    var minuteState by remember { mutableStateOf(LocalDateTime.now().minute) }
    val meridiemState by remember(hourState) {
        derivedStateOf { getMeridiem(hourState) }
    }

    Card(
        modifier = modifier.padding(bottom = 10.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onClick(
                            TimeModel(
                                hour = hourState,
                                minute = minuteState,
                                meridiem = meridiemState
                            )
                        )
                    }
                    .padding(top = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = stringResource(R.string.performance_find_lost_item_create_calendar_confirm),
                    color = Me_Pink,
                    fontSize = 16.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .height(1.dp)
                    .background(Anti_Flash_White)
            )
            Box(contentAlignment = Alignment.Center) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Picker(
                            items = (0..23).toList(),
                            itemSize = 44.dp,
                            startIndex = LocalDateTime.now().hour,
                            visibleItemsCount = 5,
                            onScrollChange = { hourState = it }
                        )
                        Text(
                            text = ":",
                            modifier = Modifier.padding(horizontal = 3.dp),
                            color = Black_Pearl,
                            fontSize = 16.dp.toSp(),
                            fontWeight = FontWeight.Bold,
                            fontFamily = spoqahansanseeo
                        )
                        Picker(
                            items = (0..59).toList(),
                            itemSize = 44.dp,
                            startIndex = LocalDateTime.now().minute,
                            visibleItemsCount = 5,
                            onScrollChange = { minuteState = it }
                        )
                    }
                    MeridiemPicker(
                        itemSize = 44.dp,
                        currentIndex = if (meridiemState == Meridiem.AM) 0 else 1,
                        visibleItemsCount = 5
                    )
                }

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp)
                        .background(Me_Pink.copy(0.1f))
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun MeridiemPicker(
    itemSize: Dp,
    currentIndex: Int,
    visibleItemsCount: Int,
) {
    val visibleItemsMiddle = visibleItemsCount / 2
    val pickerItems = List(visibleItemsMiddle) { "" } + listOf(Meridiem.AM.name, Meridiem.PM.name) + List(visibleItemsMiddle) { "" }
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = currentIndex)
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)

    val driveFirstVisibleItemIndex by remember {
        derivedStateOf { listState.firstVisibleItemIndex + 2 }
    }
    LaunchedEffect(currentIndex) {
        listState.scrollToItem(currentIndex)
    }

    LazyColumn(
        state = listState,
        flingBehavior = flingBehavior,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .wrapContentWidth()
            .height(itemSize * visibleItemsCount),
        userScrollEnabled = false
    ) {
        itemsIndexed(pickerItems) { index, item ->
            Box(
                modifier = Modifier.size(itemSize),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = item,
                    color = if (driveFirstVisibleItemIndex == index) Black_Pearl else Silver_Sand,
                    fontSize = 16.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Picker(
    items: List<Int>,
    itemSize: Dp,
    startIndex: Int,
    visibleItemsCount: Int,
    onScrollChange: (Int) -> Unit
) {
    val visibleItemsMiddle = visibleItemsCount / 2
    val pickerItems = List(visibleItemsMiddle) { "" } + items.map { it.toString() } + List(visibleItemsMiddle) { "" }
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = startIndex)
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)
    val driveFirstVisibleItemIndex by remember {
        derivedStateOf { listState.firstVisibleItemIndex + 2 }
    }
    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .distinctUntilChanged()
            .collect { index -> onScrollChange(index) }
    }

    LazyColumn(
        state = listState,
        flingBehavior = flingBehavior,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .wrapContentWidth()
            .height(itemSize * visibleItemsCount)
    ) {
        itemsIndexed(pickerItems) { index, item ->
            Box(
                modifier = Modifier.size(itemSize),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (item.isNotEmpty()) String.format("%02d", item.toInt()) else item,
                    color = if (driveFirstVisibleItemIndex == index) Black_Pearl else Silver_Sand,
                    fontSize = 16.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )
            }
        }
    }
}
