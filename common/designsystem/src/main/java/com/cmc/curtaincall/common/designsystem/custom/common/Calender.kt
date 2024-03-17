package com.cmc.curtaincall.common.designsystem.custom.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.component.buttons.common.CurtainCallFilledButton
import com.cmc.curtaincall.common.designsystem.theme.Blue
import com.cmc.curtaincall.common.designsystem.theme.CurtainCallTheme
import com.cmc.curtaincall.common.designsystem.theme.Grey1
import com.cmc.curtaincall.common.designsystem.theme.Grey4
import com.cmc.curtaincall.common.designsystem.theme.Grey8
import com.cmc.curtaincall.common.designsystem.theme.Red
import com.cmc.curtaincall.common.designsystem.theme.White
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

private val UnSelectedCalendarDay = CalendarDay(LocalDate.MIN, DayPosition.InDate)

@Composable
fun CurtainCallCalendar(
    modifier: Modifier = Modifier,
    onSelectDays: (List<CalendarDay>) -> Unit = {}
) {
    var currentMonth by remember { mutableStateOf(YearMonth.now()) }
    val startMonth = remember { currentMonth.minusMonths(12) }
    val endMonth = remember { currentMonth.plusMonths(12) }
    val firstDayOfWeek = remember { firstDayOfWeekFromLocale() }
    val coroutineScope = rememberCoroutineScope()
    val calendarState = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = firstDayOfWeek
    )

    var selectedDays: List<CalendarDay> by remember { mutableStateOf(listOf()) }
    Card(
        modifier = modifier.padding(bottom = 10.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 20.dp)
    ) {
        CurtainCallMonthHeader(
            currentMonth = currentMonth,
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
            }
        )
        HorizontalCalendar(
            modifier = Modifier
                .padding(horizontal = 13.dp)
                .padding(bottom = 20.dp),
            state = calendarState,
            dayContent = { day ->
                CurtainCallDay(
                    day = day,
                    selectedCalendarDays = selectedDays,
                    onDayClick = {
                        if (selectedDays.size == 2) {
                            selectedDays = listOf(it)
                        } else {
                            selectedDays = selectedDays + listOf(it)
                        }
                    }
                )
            }
        )
        CurtainCallFilledButton(
            text = stringResource(R.string.finish_select),
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(bottom = 20.dp)
                .fillMaxWidth()
                .height(46.dp),
            containerColor = CurtainCallTheme.colors.secondary,
            contentColor = CurtainCallTheme.colors.primary,
            textStyle = CurtainCallTheme.typography.body2.copy(
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}

@Composable
private fun CurtainCallMonthHeader(
    currentMonth: YearMonth,
    inDateClick: () -> Unit = {},
    outDateClick: () -> Unit = {}
) {
    val dayOfWeeks = listOf("일", "월", "화", "수", "목", "금", "토")
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_arrow_left_pink),
                contentDescription = null,
                modifier = Modifier
                    .size(16.dp)
                    .clickable { inDateClick() },
                tint = Grey4
            )
            Text(
                text = String.format(
                    "%d년 %d월",
                    currentMonth.year,
                    currentMonth.month.value
                ),
                modifier = Modifier.padding(horizontal = 8.dp),
                style = CurtainCallTheme.typography.subTitle4.copy(
                    color = Grey1
                )
            )
            Icon(
                painter = painterResource(R.drawable.ic_arrow_right_pink),
                contentDescription = null,
                modifier = Modifier
                    .size(16.dp)
                    .clickable { outDateClick() },
                tint = Grey4
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Grey8)
        )
        Row(
            modifier = Modifier
                .padding(horizontal = 13.dp)
                .padding(top = 6.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            dayOfWeeks.forEach { dayOfWeek ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = dayOfWeek,
                        style = CurtainCallTheme.typography.body2.copy(
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun CurtainCallDay(
    day: CalendarDay,
    selectedCalendarDays: List<CalendarDay> = listOf(),
    onDayClick: (CalendarDay) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .clickable { onDayClick(day) },
        contentAlignment = Alignment.Center
    ) {
        if (day.position == DayPosition.MonthDate) {
            Row(modifier = Modifier.matchParentSize()) {
                Spacer(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(
                            if (selectedCalendarDays.size == 2) {
                                if (selectedCalendarDays[0].date < day.date && day.date <= selectedCalendarDays[1].date) {
                                    CurtainCallTheme.colors.secondary.copy(0.4f)
                                } else {
                                    CurtainCallTheme.colors.background
                                }
                            } else {
                                CurtainCallTheme.colors.background
                            }
                        )
                )
                Spacer(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(
                            if (selectedCalendarDays.size == 2) {
                                if (selectedCalendarDays[0].date <= day.date && day.date < selectedCalendarDays[1].date) {
                                    CurtainCallTheme.colors.secondary.copy(0.4f)
                                } else {
                                    CurtainCallTheme.colors.background
                                }
                            } else {
                                CurtainCallTheme.colors.background
                            }
                        )
                )
            }
            Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    .background(
                        color = if (selectedCalendarDays.contains(day)) {
                            CurtainCallTheme.colors.secondary
                        } else {
                            if (selectedCalendarDays.size == 2 &&
                                selectedCalendarDays[0].date < day.date &&
                                day.date < selectedCalendarDays[1].date
                            ) {
                                Color.Transparent
                            } else {
                                CurtainCallTheme.colors.background
                            }
                        },
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = day.date.dayOfMonth.toString(),
                    style = CurtainCallTheme.typography.body2.copy(
                        color = when (day.date.dayOfWeek) {
                            DayOfWeek.SATURDAY -> Blue
                            DayOfWeek.SUNDAY -> Red
                            else -> Grey1
                        }.copy(
                            if (selectedCalendarDays.contains(day)) {
                                1f
                            } else {
                                if (selectedCalendarDays.size == 2 &&
                                    selectedCalendarDays[0].date < day.date &&
                                    day.date < selectedCalendarDays[1].date
                                ) {
                                    1f
                                } else {
                                    0.5f
                                }
                            }
                        )
                    )
                )
            }
        }
    }
}

@Preview
@Composable
private fun CurtainCallCalendarPreview() {
    CurtainCallTheme {
        CurtainCallCalendar(
            modifier = Modifier.size(320.dp, 384.dp)
        )
    }
}
