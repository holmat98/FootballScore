package com.mateuszholik.database.converters

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.ZoneOffset

internal class LocalDateTimeConverter {

    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDateTime? = value?.let {
        LocalDateTime.ofEpochSecond(value, 0, ZoneOffset.UTC)
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDateTime?): Long? = date?.toEpochSecond(ZoneOffset.UTC)
}
