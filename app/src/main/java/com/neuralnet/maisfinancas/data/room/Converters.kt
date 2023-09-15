package com.neuralnet.maisfinancas.data.room

import androidx.room.TypeConverter
import com.neuralnet.maisfinancas.util.toCalendar
import java.math.BigDecimal
import java.util.Calendar
import java.util.UUID

class Converters {

    @TypeConverter
    fun calendarfromDatestamp(value: Long): Calendar = value.toCalendar()

    @TypeConverter
    fun calendarToDatestamp(calendar: Calendar): Long = calendar.timeInMillis

    @TypeConverter
    fun bigDecimalfromDouble(value: Double): BigDecimal = BigDecimal.valueOf(value)

    @TypeConverter
    fun bigDecimalToDouble(bigDecimal: BigDecimal): Double = bigDecimal.toDouble()

    @TypeConverter
    fun uuidFromString(value: String): UUID = UUID.fromString(value)

    @TypeConverter
    fun uuidToString(uuid: UUID): String = uuid.toString()

}
