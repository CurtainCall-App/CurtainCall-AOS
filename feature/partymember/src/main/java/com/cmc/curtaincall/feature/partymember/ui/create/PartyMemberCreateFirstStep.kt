package com.cmc.curtaincall.feature.partymember.ui.create

import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.basic.CurtainCallBorderText
import com.cmc.curtaincall.common.design.component.basic.CurtainCallSelectTypeButton
import com.cmc.curtaincall.common.design.component.content.card.PerformanceSimpleCard
import com.cmc.curtaincall.common.design.component.content.row.SortTypeRow
import com.cmc.curtaincall.common.design.component.custom.SelectSortTypeBottomSheet
import com.cmc.curtaincall.common.design.component.custom.SelectedDateCalender
import com.cmc.curtaincall.common.design.component.custom.SortType
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.*

fun LazyGridScope.showPerformanceFirstStep(
    selectedIndex: Int,
    onChangeSelect: (Int) -> Unit
) {
    item(span = { GridItemSpan(3) }) {
        var isCheckFirstType by remember { mutableStateOf(true) }
        var sortType by remember { mutableStateOf(SortType.STAR) }
        var showDialog by remember { mutableStateOf(false) }

        if (showDialog) {
            SelectSortTypeBottomSheet(
                sortType = sortType,
                onSelectSortType = {
                    sortType = it
                    showDialog = false
                }
            )
        }

        Column {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp)
            ) {
                Text(
                    text = stringResource(R.string.partymember_create_classification),
                    color = Black_Pearl,
                    fontSize = 18.dp.toSp(),
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
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(R.string.partymember_create_select_performance),
                        modifier = Modifier.weight(1f),
                        color = Black_Pearl,
                        fontSize = 18.dp.toSp(),
                        fontWeight = FontWeight.Bold,
                        fontFamily = spoqahansanseeo
                    )
                    CurtainCallBorderText(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .padding(vertical = 4.dp),
                        text = stringResource(R.string.partymember_create_essential),
                        borderColor = Me_Pink,
                        contentColor = Me_Pink,
                        fontSize = 13.dp.toSp(),
                        radiusSize = 20.dp
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SortTypeRow(
                        modifier = Modifier.weight(1f),
                        sortType = sortType,
                        onClick = { showDialog = true }
                    )
                    Text(
                        text = "8:00 업데이트",
                        color = Silver_Sand,
                        fontSize = 10.dp.toSp(),
                        fontWeight = FontWeight.Normal,
                        fontFamily = spoqahansanseeo
                    )
                }
            }
        }
    }

    itemsIndexed(List(3) {}) { index, item ->
        PerformanceSimpleCard(
            modifier = Modifier
                .width(100.dp)
                .padding(bottom = 16.dp),
            title = "별이 빛나는 밤에",
            currentIndex = index,
            selectedIndex = selectedIndex,
            onChangeSelect = onChangeSelect
        )
    }
    item(span = { GridItemSpan(3) }) {
        Spacer(Modifier.size(70.dp))
    }
}

fun LazyGridScope.showEtcFirstStep(
    modifier: Modifier = Modifier,
    selectedDate: String,
    personnelCount: Int,
    clickedUndeterminedDate: Boolean,
    onSelectDate: (String) -> Unit,
    onClickUndeterminedDate: (Boolean) -> Unit,
    onClickPersonnel: (Int) -> Unit
) {
    item(span = { GridItemSpan(3) }) {
        var isClickedDate by remember { mutableStateOf(false) }
        Box(modifier.padding(horizontal = 5.dp)) {
            SelectDateWithButton(
                modifier = Modifier.padding(top = 15.dp),
                date = selectedDate,
                isClickedDate = isClickedDate,
                isClickedButton = clickedUndeterminedDate,
                onDateClick = { isClickedDate = it },
                onButtonClick = {
                    onClickUndeterminedDate(it)
                    onSelectDate("")
                },
                onCalenderClick = {
                    onSelectDate(it)
                    onClickUndeterminedDate(false)
                }
            )

            SelectPersonnelButton(
                modifier = Modifier.padding(top = 130.dp),
                personnelCount = personnelCount,
                onClick = onClickPersonnel
            )
        }
    }
}

@Composable
fun SelectPersonnelButton(
    modifier: Modifier = Modifier,
    personnelCount: Int,
    onClick: (Int) -> Unit
) {
    Column(modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(R.string.partymember_create_select_personnel),
                color = Black_Pearl,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo
            )
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier.border(BorderStroke(1.dp, Me_Pink), RoundedCornerShape(20.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.partymember_create_essential),
                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
                    color = Me_Pink,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
            }
        }

        Row(
            modifier = Modifier.padding(top = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    if (personnelCount > DEFAULT_PERSONNEL_COUNT) onClick(personnelCount - 1)
                },
                modifier = Modifier
                    .weight(1f)
                    .height(42.dp)
                    .clip(RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp)),
                colors = IconButtonDefaults.iconButtonColors(containerColor = Cultured)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_minus),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(42.dp)
                    .background(White),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = personnelCount.toString(),
                    color = Chinese_Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )
            }
            IconButton(
                onClick = {
                    if (personnelCount < Int.MAX_VALUE) onClick(personnelCount + 1)
                },
                modifier = Modifier
                    .weight(1f)
                    .height(42.dp)
                    .clip(RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp)),
                colors = IconButtonDefaults.iconButtonColors(containerColor = Cultured)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_plus),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified
                )
            }
        }
    }
}

@Composable
private fun SelectDateWithButton(
    modifier: Modifier = Modifier,
    date: String,
    isClickedDate: Boolean,
    isClickedButton: Boolean,
    onDateClick: (Boolean) -> Unit,
    onButtonClick: (Boolean) -> Unit,
    onCalenderClick: (String) -> Unit,
) {
    val context = LocalContext.current
    Column(modifier.zIndex(if (isClickedDate) 1f else 0f)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(R.string.partymember_create_select_date),
                color = Black_Pearl,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo
            )
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier.border(BorderStroke(1.dp, Me_Pink), RoundedCornerShape(20.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.partymember_create_essential),
                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
                    color = Me_Pink,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
            }
        }

        Row(
            modifier = Modifier.padding(top = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier
                    .size(200.dp, 42.dp)
                    .clickable { onDateClick(isClickedDate.not()) },
                shape = RoundedCornerShape(10.dp),
                color = Cultured
            ) {
                Text(
                    text = date.ifEmpty { stringResource(R.string.partymember_create_select_date) },
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .padding(vertical = 11.dp),
                    color = if (date.isEmpty()) Roman_Silver else Eerie_Black,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .size(110.dp, 42.dp)
                    .background(
                        if (isClickedButton) Me_Pink else Cultured,
                        RoundedCornerShape(10.dp)
                    )
                    .clickable {
                        onButtonClick(isClickedButton.not())
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.partymember_create_undetermined_date),
                    color = if (isClickedButton) White else Roman_Silver,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
            }
        }

        if (isClickedDate) {
            SelectedDateCalender(
                modifier = Modifier.padding(top = 10.dp),
                onDateClick = {
                    onCalenderClick(
                        String.format(
                            context.getString(R.string.partymember_create_calendar_date_format),
                            it.date.year,
                            it.date.month.value,
                            it.date.dayOfMonth
                        )
                    )
                    onDateClick(false)
                }
            )
        }
    }
}

@Composable
private fun PerformanceCard(
    @DrawableRes imageRes: Int,
    title: String,
    currentIndex: Int,
    selectedIndex: Int,
    onChangeSelect: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 7.dp)
            .padding(bottom = 22.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier.clickable {
                onChangeSelect(if (currentIndex == selectedIndex) -1 else currentIndex)
            },
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(
                width = 4.dp,
                color = if (selectedIndex == currentIndex) Me_Pink else Color.Transparent
            )
        ) {
            Image(
                painter = painterResource(imageRes),
                contentDescription = null
            )
        }

        Text(
            text = title,
            modifier = Modifier.padding(top = 8.dp),
            color = Eerie_Black,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo
        )
    }
}
