package com.cmc.curtaincall.common.utility.extensions

import com.cmc.curtaincall.domain.model.show.ShowTimeModel

enum class ShowDay(
    val dayOfWeek: String,
    val id: Int,
    val label: String
) {
    Monday(
        dayOfWeek = "MONDAY",
        id = 0,
        label = "월"
    ),
    Tuesday(
        dayOfWeek = "TUESDAY",
        id = 1,
        label = "화"
    ),
    Wednesday(
        dayOfWeek = "WEDNESDAY",
        id = 2,
        label = "수"
    ),
    Thursday(
        dayOfWeek = "THURSDAY",
        id = 3,
        label = "목"
    ),
    Friday(
        dayOfWeek = "FRIDAY",
        id = 4,
        label = "금"
    ),
    Saturday(
        dayOfWeek = "SATURDAY",
        id = 5,
        label = "토"
    ),
    Sunday(
        dayOfWeek = "SUNDAY",
        id = 6,
        label = "일"
    ),
    Holiday(
        dayOfWeek = "HOL",
        id = 7,
        label = "공휴일"
    )
}

fun String.toShowDay() =
    when (this) {
        ShowDay.Monday.dayOfWeek -> ShowDay.Monday
        ShowDay.Tuesday.dayOfWeek -> ShowDay.Tuesday
        ShowDay.Wednesday.dayOfWeek -> ShowDay.Wednesday
        ShowDay.Thursday.dayOfWeek -> ShowDay.Thursday
        ShowDay.Friday.dayOfWeek -> ShowDay.Friday
        ShowDay.Saturday.dayOfWeek -> ShowDay.Saturday
        ShowDay.Sunday.dayOfWeek -> ShowDay.Sunday
        else -> ShowDay.Holiday
    }

fun List<ShowTimeModel>.getShowTimes(): String {
    return this.groupBy { it.dayOfWeek }
        .map { it.key.toShowDay() to it.value.map { it.time.substring(0, 5) } }
        .sortedBy { it.first.id }
        .groupBy { it.second.joinToString(", ") }
        .map { it.value.map { it.first.label } to it.key }
        .map { it.first.joinToString(", ") + " | " + it.second }
        .joinToString("\n")
}
