package com.cmc.curtaincall.feature.partymember.ui.create

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.CurtainCallCalendar
import com.cmc.curtaincall.common.design.theme.*
import com.cmc.curtaincall.feature.partymember.PartyType
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import java.time.LocalDate

fun LazyGridScope.showSecondStep(
    modifier: Modifier = Modifier,
    partyType: PartyType,
    selectedDate: String,
    selectedTime: String,
    onSelectDate: (String) -> Unit = {},
    onSelectTime: (String) -> Unit = {}
) {
    if (partyType == PartyType.ETC) {
    } else {
        showPerformanceSecondStep(
            modifier = modifier,
            selectedDate = selectedDate,
            selectedTime = selectedTime,
            onSelectDate = onSelectDate,
            onSelectTime = onSelectTime
        )
    }
}

private fun LazyGridScope.showPerformanceSecondStep(
    modifier: Modifier = Modifier,
    selectedDate: String,
    selectedTime: String,
    onSelectDate: (String) -> Unit,
    onSelectTime: (String) -> Unit
) {
    item(span = { GridItemSpan(3) }) {
        val context = LocalContext.current
        CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
            Box(
                modifier = modifier.padding(horizontal = 6.dp),
            ) {
                var isClickedDate by remember { mutableStateOf(false) }
                var isClickedTime by remember { mutableStateOf(false) }
                SelectEditInput(
                    modifier = Modifier.padding(top = 14.dp),
                    description = stringResource(R.string.partymember_create_select_performance_date),
                    subDescription = stringResource(R.string.partymember_create_select_date),
                    input = selectedDate,
                    isClicked = isClickedDate,
                    onClick = { isClickedDate = isClickedDate.not() }
                ) {
                    CurtainCallCalendar(
                        modifier = Modifier.padding(top = 10.dp),
                        calendarDays = listOf(
                            CalendarDay(LocalDate.now(), DayPosition.MonthDate),
                            CalendarDay(LocalDate.of(2023, 7, 18), DayPosition.MonthDate),
                            CalendarDay(LocalDate.of(2023, 7, 17), DayPosition.MonthDate),
                            CalendarDay(LocalDate.of(2023, 7, 16), DayPosition.MonthDate),
                            CalendarDay(LocalDate.of(2023, 7, 15), DayPosition.MonthDate),
                            CalendarDay(LocalDate.of(2023, 7, 14), DayPosition.MonthDate),
                            CalendarDay(LocalDate.of(2023, 7, 13), DayPosition.MonthDate),
                        ),
                        onDateClick = {
                            onSelectDate(
                                String.format(
                                    context.getString(R.string.partymember_create_calendar_date_format),
                                    it.date.year,
                                    it.date.month.value,
                                    it.date.dayOfMonth
                                )
                            )
                            isClickedDate = false
                        }
                    )
                }

                SelectEditInput(
                    modifier = Modifier.padding(top = 123.dp),
                    description = stringResource(R.string.partymember_create_select_time),
                    subDescription = stringResource(R.string.partymember_create_select_time),
                    input = selectedTime,
                    isClicked = isClickedTime,
                    onClick = { isClickedTime = isClickedTime.not() }
                ) {
                    DropDownTime(
                        modifier = Modifier.padding(top = 10.dp),
                        times = listOf("14:00", "19:00", "20:00"),
                        onClick = {
                            onSelectTime(it)
                            isClickedTime = false
                        }
                    )
                }

                PersonnelButton(
                    modifier = Modifier.padding(top = 232.dp)
                )
            }
        }
    }
}

@Composable
private fun DropDownTime(
    modifier: Modifier = Modifier,
    times: List<String> = listOf(),
    onClick: (String) -> Unit = {}
) {
    Card(
        modifier = modifier.padding(bottom = 10.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        times.forEachIndexed { index, time ->
            Text(
                text = time,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp)
                    .padding(vertical = 18.dp)
                    .clickable { onClick(time) },
                color = Gunmetal,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            )

            if (index != times.lastIndex) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp)
                        .height(1.dp)
                        .background(Gunmetal)
                )
            }
        }
    }
}

@Composable
private fun SelectEditInput(
    modifier: Modifier = Modifier,
    description: String,
    subDescription: String,
    input: String,
    isClicked: Boolean,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    Column(modifier = modifier.zIndex(if (isClicked) 1f else 0f)) {
        Row {
            Text(
                text = description,
                color = Eerie_Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo
            )

            Spacer(modifier = Modifier.weight(1f))

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
        }

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 9.dp)
                .height(40.dp)
                .clickable { onClick() },
            shape = RoundedCornerShape(10.dp),
            color = Cultured
        ) {
            Text(
                text = if (input.isEmpty()) subDescription else input,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .padding(vertical = 11.dp),
                color = if (input.isEmpty()) Roman_Silver else Eerie_Black,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            )
        }

        if (isClicked) {
            content()
        }
    }
}

@Composable
private fun PersonnelButton(
    modifier: Modifier = Modifier
) {
    var personnel by remember { mutableStateOf(1) }
    Column(modifier = modifier) {
        Row {
            Text(
                text = stringResource(R.string.partymember_create_select_personnel),
                color = Eerie_Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo
            )
            Spacer(Modifier.weight(1f))
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
        }

        Row(
            modifier = Modifier
                .padding(top = 16.dp)
                .height(40.dp)
        ) {
            IconButton(
                onClick = { personnel-- },
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp)),
                enabled = personnel > 1,
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Cultured,
                    disabledContainerColor = Cultured
                )
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_minus),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .padding(horizontal = 42.dp),
                    tint = Arsenic
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = personnel.toString(),
                    color = Chinese_Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )
            }

            IconButton(
                onClick = { personnel++ },
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp)),
                enabled = personnel < Int.MAX_VALUE,
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Cultured,
                    disabledContainerColor = Cultured
                )
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_plus),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .padding(horizontal = 42.dp),
                    tint = Arsenic
                )
            }
        }
    }
}
