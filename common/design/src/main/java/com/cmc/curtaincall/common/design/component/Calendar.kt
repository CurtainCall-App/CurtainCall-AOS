package com.cmc.curtaincall.common.design.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.sp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.theme.*
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.*
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.YearMonth

private const val UNSELECTED_INDEX = -1

@Composable
fun CurtainCallCalendar(
    modifier: Modifier = Modifier,
    calendarDays: List<CalendarDay> = listOf(),
    onDateClick: (CalendarDay) -> Unit = { }
) {
    var currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(100) }
    val endMonth = remember { currentMonth.plusMonths(100) }
    val firstDayOfWeek = remember { firstDayOfWeekFromLocale() }

    val coroutineScope = rememberCoroutineScope()
    val calendarState = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = firstDayOfWeek
    )
    var selectedDayIndex by remember { mutableStateOf(UNSELECTED_INDEX) }
    Card(
        modifier = modifier.padding(bottom = 10.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        HorizontalCalendar(
            state = calendarState,
            dayContent = { day ->
                Day(
                    day = day,
                    calendarDays = calendarDays,
                    selectedDayIndex = selectedDayIndex,
                    onClick = { selectedDayIndex = it }
                )
            },
            monthHeader = { calendarMonth ->
                MonthHeader(
                    calendarMonth = calendarMonth,
                    inDateClick = {
                        coroutineScope.launch {
                            currentMonth = currentMonth.minusMonths(1)
                            calendarState.animateScrollToMonth(currentMonth)
                        }
                    },
                    outDateClick = {
                        coroutineScope.launch {
                            currentMonth = currentMonth.plusMonths(1)
                            calendarState.animateScrollToMonth(currentMonth)
                        }
                    },
                    onClick = {
                        if (selectedDayIndex != UNSELECTED_INDEX) {
                            onDateClick(calendarDays[selectedDayIndex])
                        }
                    }
                )
            }
        )
    }
}

@Composable
private fun MonthHeader(
    calendarMonth: CalendarMonth,
    inDateClick: () -> Unit,
    outDateClick: () -> Unit,
    onClick: () -> Unit
) {
    val dayOfWeeks = listOf("일", "월", "화", "수", "목", "금", "토")
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { inDateClick() },
                modifier = Modifier.size(15.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_left_pink),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }

            Text(
                text = String.format(
                    stringResource(R.string.partymember_create_calendar_year_format),
                    calendarMonth.yearMonth.year,
                    calendarMonth.yearMonth.month.value
                ),
                modifier = Modifier.padding(horizontal = 9.dp),
                color = Chinese_Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo
            )

            IconButton(
                onClick = { outDateClick() },
                modifier = Modifier.size(15.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_right_pink),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .size(46.dp, 24.dp)
                    .background(White, RoundedCornerShape(20.dp))
                    .border(1.dp, Me_Pink, RoundedCornerShape(20.dp))
                    .clickable { onClick() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.partymember_create_calendar_confirm),
                    color = Me_Pink,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Anti_Flash_White)
        )

        Row(modifier = Modifier.padding(top = 10.dp)) {
            dayOfWeeks.forEach { dayOfWeek ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1.2f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = dayOfWeek,
                        color = Gunmetal,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo,
                    )
                }
            }
        }
    }
}

@Composable
private fun Day(
    day: CalendarDay,
    selectedDayIndex: Int,
    calendarDays: List<CalendarDay> = listOf(),
    onClick: (Int) -> Unit
) {
    Box(
        modifier = Modifier.aspectRatio(1.2f),
        contentAlignment = Alignment.Center
    ) {
        if (day.position == DayPosition.MonthDate) {
            Text(
                text = day.date.dayOfMonth.toString(),
                modifier = Modifier
                    .background(if (day in calendarDays && selectedDayIndex == calendarDays.indexOf(day)) Me_Pink else Color.Transparent, CircleShape)
                    .clickable {
                        if (day in calendarDays) {
                            onClick(
                                if (selectedDayIndex == calendarDays.indexOf(day)) UNSELECTED_INDEX else calendarDays.indexOf(day)
                            )
                        }
                    }
                    .padding(5.dp),
                color = if (day.date.dayOfWeek == DayOfWeek.SUNDAY) {
                    Red_Salsa.copy(if (day in calendarDays) 1f else 0.5f)
                } else if (day.date.dayOfWeek == DayOfWeek.SATURDAY) {
                    Ultramarine_Blue.copy(if (day in calendarDays) 1f else 0.5f)
                } else {
                    if (day in calendarDays) {
                        Gunmetal
                    } else {
                        Silver_Sand
                    }
                },
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            )
        }
    }
}
