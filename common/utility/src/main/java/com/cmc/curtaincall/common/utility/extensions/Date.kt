package com.cmc.curtaincall.common.utility.extensions

import java.text.SimpleDateFormat
import java.util.Calendar

fun String.toDateWithDay(): String {
    if (isEmpty()) return ""
    val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(this)
    return SimpleDateFormat("yyyy.MM.dd(E)").format(date)
}

fun String.toTime(): String {
    if (isEmpty()) return ""
    val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(this)
    return SimpleDateFormat("HH:mm").format(date)
}

fun String.toDday(): Long {
    if (isEmpty()) return 0L
    val date = SimpleDateFormat("yyyy-MM-dd").parse(this)
    val dday = (Calendar.getInstance().time.time - date.time) / (60 * 60 * 24 * 1000)
    return dday
}

fun String.toChangeDate(): String {
    if (isEmpty()) return ""
    val date = SimpleDateFormat("yyyy-MM-dd").parse(this)
    return SimpleDateFormat("yy.MM.dd").format(date)
}

fun String.toChangeFullDate(): String {
    if (isEmpty()) return ""
    val date = SimpleDateFormat("yyyy-MM-dd").parse(this)
    return SimpleDateFormat("yyyy.MM.dd").format(date)
}

fun String.toChangeServerDate(): String {
    if (isEmpty()) return ""
    val date = SimpleDateFormat("yyyy.MM.dd").parse(this)
    return SimpleDateFormat("yyyy-MM-dd").format(date)
}

fun changeShowAt(date: String, time: String): String {
    val showDate = SimpleDateFormat("yyyy. MM. dd").parse(date)
    val changeDate = SimpleDateFormat("yyyy-MM-dd").format(showDate)
    return "${changeDate}T$time:00"
}
