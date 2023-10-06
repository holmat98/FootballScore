package com.mateuszholik.uicomponents.extensions

import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

internal fun LocalDate.asHourString(): String {

    val dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM")

    return this.format(dateTimeFormatter)
}

internal fun LocalDate.getShortDateString(): String =
    "${dayOfWeek.getShortName()} ${month.getShortName()} $year"

internal fun LocalDate.toEpochSecond(): Long =
    atStartOfDay(ZoneId.systemDefault()).toEpochSecond()
