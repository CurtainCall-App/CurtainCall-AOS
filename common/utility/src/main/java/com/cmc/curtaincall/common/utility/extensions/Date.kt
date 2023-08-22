package com.cmc.curtaincall.common.utility.extensions

import java.text.SimpleDateFormat
import java.util.Calendar

fun String.toDateWithDay(): String {
    val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(this)
    return SimpleDateFormat("yyyy.MM.dd(E)").format(date)
}

fun String.toTime(): String {
    val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(this)
    return SimpleDateFormat("HH:mm").format(date)
}

fun String.toDday(): Long {
    val date = SimpleDateFormat("yyyy-MM-dd").parse(this)
    val dday = (Calendar.getInstance().time.time - date.time) / (60 * 60 * 24 * 1000)
    return dday
}
