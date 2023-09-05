package com.cmc.curtaincall.common.utility.extensions

import com.cmc.curtaincall.domain.model.show.ShowTimeModel
import java.text.SimpleDateFormat
import java.util.Calendar

fun String.toRunningTime(): Int {
    if (isEmpty()) return 0
    var time = 0
    if (contains("시간")) {
        val hourIdx = indexOf("시간")
        time += (substring(0, hourIdx).toInt() * 60)
        if (contains("분")) {
            var minuteIdx = indexOf("분")
            time += substring(hourIdx + 3, minuteIdx).toInt()
        }
    } else {
        var minuteIdx = indexOf("분")
        time += substring(0, minuteIdx).toInt()
    }
    return time
}

fun String.getTimes(showTimeModels: List<ShowTimeModel>): List<String> {
    val date = SimpleDateFormat("yyyy. MM. dd").parse(this)
    val calendar = Calendar.getInstance().apply { time = date }

    val monday = calendar.get(Calendar.DAY_OF_WEEK)
    val dayOfWeek = when (monday) {
        1 -> "SUNDAY"
        2 -> "MONDAY"
        3 -> "TUESDAY"
        4 -> "WEDNESDAY"
        5 -> "THURSDAY"
        6 -> "FRIDAY"
        else -> "SATURDAY"
    }
    return showTimeModels
        .filter { it.dayOfWeek == dayOfWeek }
        .map { it.time.substring(0, 5) }
}
