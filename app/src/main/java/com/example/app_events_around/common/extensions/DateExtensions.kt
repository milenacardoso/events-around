package com.example.app_events_around.common.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Long.convertToDateFormat(format: String = "dd/MM/yy hh:mm"): String {
    val sdf = SimpleDateFormat(format, Locale.getDefault())
    val newDate = Date(this)
    return sdf.format(newDate)
}