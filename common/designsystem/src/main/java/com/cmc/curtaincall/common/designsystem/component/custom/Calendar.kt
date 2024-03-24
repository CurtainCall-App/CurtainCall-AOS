package com.cmc.curtaincall.common.designsystem.component.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.extensions.toSp
import com.cmc.curtaincall.common.designsystem.theme.Anti_Flash_White
import com.cmc.curtaincall.common.designsystem.theme.Chinese_Black
import com.cmc.curtaincall.common.designsystem.theme.CurtainCallTheme
import com.cmc.curtaincall.common.designsystem.theme.Gunmetal
import com.cmc.curtaincall.common.designsystem.theme.Me_Pink
import com.cmc.curtaincall.common.designsystem.theme.Red_Salsa
import com.cmc.curtaincall.common.designsystem.theme.Silver_Sand
import com.cmc.curtaincall.common.designsystem.theme.Ultramarine_Blue
import com.cmc.curtaincall.common.designsystem.theme.White
import com.cmc.curtaincall.common.designsystem.theme.spoqahansanseeo
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
fun PreviousDateCalendar(
    modifier: Modifier = Modifier,
    onDateClick: (CalendarDay) -> Unit = {}
) {
    var currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(3) }
    val endMonth = remember { currentMonth.plusMonths(3) }
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
                Box(
                    modifier = Modifier.aspectRatio(1.2f),
                    contentAlignment = Alignment.Center
                ) {
                    Day(
                        day = day,
                        selectedCalendarDay = selectedCalendarDay,
                        currentCalendarDay = CalendarDay(LocalDate.now(), DayPosition.MonthDate),
                        onDateClick = { selectedCalendarDay = it }
                    )
                }
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
fun SelectedDateCalender(
    modifier: Modifier = Modifier,
    calendarDays: List<CalendarDay> = listOf(),
    onDateClick: (CalendarDay) -> Unit = { }
) {
    var currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(3) }
    val endMonth = remember { currentMonth.plusMonths(3) }
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
                Box(
                    modifier = Modifier.aspectRatio(1.2f),
                    contentAlignment = Alignment.Center
                ) {
                    Day(
                        day = day,
                        calendarDays = calendarDays,
                        selectedCalendarDay = selectedCalendarDay,
                        onClick = { selectedCalendarDay = it }
                    )
                }
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
                fontSize = 16.dp.toSp(),
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
                fontSize = 16.dp.toSp(),
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
                        fontFamily = spoqahansanseeo
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
    currentCalendarDay: CalendarDay,
    onDateClick: (CalendarDay) -> Unit
) {
    Box(
        modifier = Modifier
            .background(
                color = if ((day.date <= currentCalendarDay.date) && (day == selectedCalendarDay)) Me_Pink else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable {
                if (day.position == DayPosition.MonthDate) {
                    if (day.date <= currentCalendarDay.date) {
                        onDateClick(if (day == selectedCalendarDay) UNSELECTED_CALENDARDAY else day)
                    }
                }
            }
            .aspectRatio(1f),
        contentAlignment = Alignment.Center
    ) {
        if (day.position == DayPosition.MonthDate) {
            Text(
                text = day.date.dayOfMonth.toString(),
                modifier = Modifier.align(Alignment.Center),
                color = getDayColor(
                    enabled = day.date <= currentCalendarDay.date,
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
            .aspectRatio(1f),
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

@Preview
@Composable
private fun PreviousDateCalendarPreview() {
    CurtainCallTheme {
        PreviousDateCalendar(
            modifier = Modifier.size(320.dp, 384.dp),
            onDateClick = {}
        )
    }
}
