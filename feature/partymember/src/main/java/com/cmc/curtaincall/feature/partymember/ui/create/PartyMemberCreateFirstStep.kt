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
import com.cmc.curtaincall.common.design.component.SelectedDateCalender
import com.cmc.curtaincall.common.design.theme.*
import com.cmc.curtaincall.feature.partymember.PartyPerformanceType

enum class SortType {
    Reservation, Korean
}

fun LazyGridScope.showPerformanceFirstStep(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    onChangeSelect: (Int) -> Unit
) {
    val dummyDatas = listOf(
        "BIRTH" to R.drawable.img_default_performance,
        "BIRTH" to R.drawable.img_default_performance,
        "BIRTH" to R.drawable.img_default_performance,
        "BIRTH" to R.drawable.img_default_performance,
        "BIRTH" to R.drawable.img_default_performance,
        "BIRTH" to R.drawable.img_default_performance,
        "BIRTH" to R.drawable.img_default_performance,
        "BIRTH" to R.drawable.img_default_performance,
        "BIRTH" to R.drawable.img_default_performance,
        "BIRTH" to R.drawable.img_default_performance,
        "BIRTH" to R.drawable.img_default_performance,
        "BIRTH" to R.drawable.img_default_performance,
        "BIRTH" to R.drawable.img_default_performance,
        "BIRTH" to R.drawable.img_default_performance
    )

    item(span = { GridItemSpan(3) }) {
        Column {
            PerformanceClassifyButton(
                modifier = modifier
                    .padding(top = 15.dp)
                    .padding(horizontal = 6.dp)
            )

            PerformanceGridHeader(
                modifier = modifier
                    .padding(top = 36.dp)
                    .padding(horizontal = 6.dp)
            )

            Spacer(modifier = Modifier.size(24.dp))
        }
    }

    itemsIndexed(dummyDatas) { index, item ->
        PerformanceCard(
            imageRes = item.second,
            title = item.first,
            currentIndex = index,
            selectedIndex = selectedIndex,
            onChangeSelect = onChangeSelect
        )
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
private fun PerformanceClassifyButton(
    modifier: Modifier = Modifier
) {
    var performanceType by remember { mutableStateOf(PartyPerformanceType.THEATER) }
    Column(modifier) {
        Text(
            text = stringResource(R.string.partymember_create_classification),
            color = Eerie_Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = spoqahansanseeo
        )

        Row {
            Button(
                onClick = { performanceType = PartyPerformanceType.THEATER },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (performanceType == PartyPerformanceType.THEATER) Me_Pink else White
                ),
                border = BorderStroke(1.dp, if (performanceType == PartyPerformanceType.THEATER) French_Pink else Silver_Sand)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.partymember_create_classification_theater),
                        color = if (performanceType == PartyPerformanceType.THEATER) White else Silver_Sand,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = spoqahansanseeo
                    )
                }
            }

            Button(
                onClick = { performanceType = PartyPerformanceType.MUSICAL },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (performanceType == PartyPerformanceType.MUSICAL) Me_Pink else White
                ),
                border = BorderStroke(1.dp, if (performanceType == PartyPerformanceType.MUSICAL) French_Pink else Silver_Sand)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.partymember_create_classification_musical),
                        color = if (performanceType == PartyPerformanceType.MUSICAL) White else Silver_Sand,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = spoqahansanseeo
                    )
                }
            }
        }
    }
}

@Composable
private fun PerformanceGridHeader(
    modifier: Modifier = Modifier,
    onChangeSort: (SortType) -> Unit = {}
) {
    var sortTypeState by remember { mutableStateOf(SortType.Reservation) }
    Row(modifier) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = stringResource(R.string.partymember_create_select_performance),
                color = Eerie_Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo
            )

            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(
                    modifier = Modifier
                        .size(4.dp)
                        .background(
                            color = if (sortTypeState == SortType.Reservation) {
                                Fiery_Rose
                            } else {
                                Color.Transparent
                            }
                        )
                        .align(Alignment.CenterVertically)
                        .clickable { sortTypeState = SortType.Reservation }
                )

                Text(
                    text = stringResource(R.string.partymember_create_reservation_sort),
                    modifier = Modifier.padding(start = 4.dp),
                    color = if (sortTypeState == SortType.Reservation) {
                        Eerie_Black
                    } else {
                        Silver_Sand
                    },
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )

                Spacer(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .size(4.dp)
                        .background(
                            color = if (sortTypeState == SortType.Korean) {
                                Fiery_Rose
                            } else {
                                Color.Transparent
                            }
                        )
                        .align(Alignment.CenterVertically)
                        .clickable { sortTypeState = SortType.Korean }
                )

                Text(
                    text = stringResource(R.string.partymember_create_korean_sort),
                    modifier = Modifier.padding(start = 4.dp),
                    color = if (sortTypeState == SortType.Korean) {
                        Eerie_Black
                    } else {
                        Silver_Sand
                    },
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
            }
        }

        Column(horizontalAlignment = Alignment.End) {
            Surface(
                modifier = Modifier.size(38.dp, 20.dp),
                shape = RoundedCornerShape(20.dp),
                color = White,
                border = BorderStroke(1.dp, Me_Pink)
            ) {
                Text(
                    text = stringResource(R.string.partymember_create_essential),
                    modifier = Modifier.padding(vertical = 2.dp, horizontal = 8.dp),
                    color = Me_Pink,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
            }

            Text(
                text = stringResource(R.string.partymember_create_update_time_format),
                modifier = Modifier.padding(top = 9.dp),
                color = Silver_Sand,
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
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
                contentDescription = null,
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
