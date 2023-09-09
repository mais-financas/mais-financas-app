package com.neuralnet.maisfinancas.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun Long.toCalendar(): Calendar = Calendar.getInstance()
    .apply { timeInMillis = this@toCalendar }

fun Calendar.formattedDate(): String {
    return try {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR"))
        sdf.format(Date(this.timeInMillis))
    } catch (e: Exception) {
        e.toString()
    }
}