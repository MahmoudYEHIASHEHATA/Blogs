package com.shahry.common

import java.text.SimpleDateFormat
import java.util.*


fun Long.getFormatTime(): String? {
    val currentTime = Date(this * 1000)
    val timeZoneDate = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    return timeZoneDate.format(currentTime)
}