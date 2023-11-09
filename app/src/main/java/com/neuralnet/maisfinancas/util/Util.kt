package com.neuralnet.maisfinancas.util

import java.math.BigDecimal
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Currency
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

fun BigDecimal.toReal(): String {
    val format: NumberFormat = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = 2
    format.currency = Currency.getInstance(Locale("pt", "BR"))

    return format.format(this)
}

fun Calendar.formattedMonth(): String {
    return try {
        val locale = Locale("pt", "BR")
        val sdf = SimpleDateFormat("MMMM yyyy", locale)
        sdf.format(Date(this.timeInMillis))
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(locale) else it.toString() }
    } catch (e: Exception) {
        e.toString()
    }
}

fun Calendar.displayMonth(): String {
    return try {
        val locale = Locale("pt", "BR")
        val sdf = SimpleDateFormat("MMMM", locale)
        sdf.format(Date(this.timeInMillis))
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(locale) else it.toString() }
    } catch (e: Exception) {
        e.toString()
    }
}

fun Calendar.toMonthQuery(): String {
    val ano = get(Calendar.YEAR)
    val mes = get(Calendar.MONTH) + 1 // Meses começam em 0
    return String.format("%04d-%02d", ano, mes)
}
