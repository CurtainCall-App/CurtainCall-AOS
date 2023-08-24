package com.cmc.curtaincall.common.utility.extensions

import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar

fun getCalendarDays(startDate: String, endDate: String): List<CalendarDay> {
    var start = SimpleDateFormat("yyyy-MM-dd").parse(startDate)
    val end = SimpleDateFormat("yyyy-MM-dd").parse(endDate)

    val startCalendar = Calendar.getInstance().apply { time = start }
    val calendarDays = mutableListOf<CalendarDay>()
    while (start <= end) {
        val calendar = Calendar.getInstance().apply { time = start }
        calendarDays.add(
            CalendarDay(
                LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH)),
                DayPosition.MonthDate
            )
        )
        calendar.add(Calendar.DATE, 1)
        start = calendar.time
    }
    return calendarDays.toList()
}
