package com.cmc.curtaincall.common.utility.extensions

fun String.toRunningTime(): Int {
    if (isEmpty()) return 0
    var time = 0
    if (contains("시간")) {
        val hourIdx = indexOf("시간")
        time += substring(0, hourIdx).toInt()
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
