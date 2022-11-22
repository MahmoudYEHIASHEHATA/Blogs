package com.shahry.feature.core

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


fun String.formatToEgyptianDateTimeDefaults(): String{
    val sdf= SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss", Locale.getDefault())
    val egDate = try {
        sdf.parse(this)
    } catch (e: ParseException) {
        e.printStackTrace()
        Date()
    }

    return sdf.format(egDate)
}

