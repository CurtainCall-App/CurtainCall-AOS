package com.cmc.curtaincall.domain.model.show

import java.lang.StringBuilder

data class ShowTimeModel(
    val dayOfWeek: String = "",
    val time: String = ""
)

fun List<ShowTimeModel>.toPrint(): String {
    val weekdaysMap = mapOf(
        "MONDAY" to "월",
        "TUESDAY" to "화",
        "WEDNESDAY" to "수",
        "THURSDAY" to "목",
        "FRIDAY" to "금"
    )

    val result = StringBuilder()
    val days = mutableListOf<String>()
    forEach {
        weekdaysMap.get(it.dayOfWeek)?.let { day ->
            days.add(day)
        }
    }
    if (days.count() == 1) {
        val time = find { weekdaysMap[it.dayOfWeek] == days.first() }?.time?.substring(0, 5) ?: "00:00"
        result.append("${days.first()} $time")
    } else {
        val time = find { weekdaysMap[it.dayOfWeek] == days.first() }?.time?.substring(0, 5) ?: "00:00"
        result.append("${days.first()}-${days.last()} $time")
    }

    val holidays = mutableListOf<String>()
    if (find { it.dayOfWeek == "SATURDAY" } != null) holidays.add("토")
    if (find { it.dayOfWeek == "SUNDAY" } != null) holidays.add("일")
    val weekendTime = filter { it.dayOfWeek == "SATURDAY" }.map { it.time.substring(0, 5) }

    if (holidays.isNotEmpty()) {
        result.append("\n")
        result.append("${holidays.joinToString(", ")} ")
        result.append(weekendTime.joinToString(", "))
    }
    return result.toString()
}
