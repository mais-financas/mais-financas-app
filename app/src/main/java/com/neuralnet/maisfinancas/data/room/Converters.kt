package com.neuralnet.maisfinancas.data.room

import androidx.room.TypeConverter
import com.neuralnet.maisfinancas.util.toCalendar
import java.math.BigDecimal
import java.util.Calendar
import java.util.UUID

class Converters {

    @TypeConverter
    fun calendarFromDatestamp(value: Long): Calendar = value.toCalendar()

    @TypeConverter
    fun calendarToDatestamp(calendar: Calendar): Long = calendar.timeInMillis

    @TypeConverter
    fun bigDecimalFromLong(valueInCents: Long): BigDecimal = BigDecimal.valueOf(valueInCents)
        .divide(BigDecimal.valueOf(100))

    @TypeConverter
    fun bigDecimalToDouble(bigDecimal: BigDecimal): Long = bigDecimal.multiply(
        BigDecimal.valueOf(100)).longValueExact()

    @TypeConverter
    fun uuidFromString(value: String): UUID = UUID.fromString(value)

    @TypeConverter
    fun uuidToString(uuid: UUID): String = uuid.toString()

}
