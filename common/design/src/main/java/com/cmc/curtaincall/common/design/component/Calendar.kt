package com.cmc.curtaincall.common.design.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

private val UNSELECTED_CALENDARDAY = CalendarDay(LocalDate.MIN, DayPosition.InDate)

@Composable
fun SelectedDateCalender(
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
    var selectedCalendarDay by remember { mutableStateOf(UNSELECTED_CALENDARDAY) }

    Card(
        modifier = modifier.padding(bottom = 10.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        HorizontalCalendar(
            modifier = Modifier.padding(bottom = 13.dp),
            state = calendarState,
            dayContent = { day ->
                Day(
                    day = day,
                    calendarDays = calendarDays,
                    selectedCalendarDay = selectedCalendarDay,
                    onClick = { selectedCalendarDay = it }
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
                        if (selectedCalendarDay != UNSELECTED_CALENDARDAY) {
                            onDateClick(selectedCalendarDay)
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

            Text(
                text = stringResource(R.string.partymember_create_calendar_confirm),
                modifier = Modifier.clickable { onClick() },
                color = Me_Pink,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo
            )
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
    selectedCalendarDay: CalendarDay,
    calendarDays: List<CalendarDay> = listOf(),
    onClick: (CalendarDay) -> Unit
) {
    Box(
        modifier = Modifier
            .background(
                color = if (((day in calendarDays) or calendarDays.isEmpty()) and (day == selectedCalendarDay)) Me_Pink else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable {
                if (day.position == DayPosition.MonthDate) {
                    if (calendarDays.isEmpty()) {
                        onClick(day)
                    } else {
                        if (day in calendarDays) {
                            onClick(if (selectedCalendarDay == day) UNSELECTED_CALENDARDAY else day)
                        }
                    }
                }
            }
            .size(40.dp),
        contentAlignment = Alignment.Center
    ) {
        if (day.position == DayPosition.MonthDate) {
            Text(
                text = day.date.dayOfMonth.toString(),
                modifier = Modifier.align(Alignment.Center),
                color = getDayColor(
                    enabled = if (calendarDays.isEmpty()) true else day in calendarDays,
                    isSelected = selectedCalendarDay == day,
                    dayOfWeek = day.date.dayOfWeek
                ),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            )
        }
    }
}

private fun getDayColor(
    enabled: Boolean,
    isSelected: Boolean,
    dayOfWeek: DayOfWeek,
): Color {
    if (isSelected) {
        return White
    } else {
        if (enabled) {
            return when (dayOfWeek) {
                DayOfWeek.SUNDAY -> Red_Salsa
                DayOfWeek.SATURDAY -> Ultramarine_Blue
                else -> Gunmetal
            }
        } else {
            return when (dayOfWeek) {
                DayOfWeek.SUNDAY -> Red_Salsa.copy(alpha = 0.5f)
                DayOfWeek.SATURDAY -> Ultramarine_Blue.copy(alpha = 0.5f)
                else -> Silver_Sand
            }
        }
    }
}
