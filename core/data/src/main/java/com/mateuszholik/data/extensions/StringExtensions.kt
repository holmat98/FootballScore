package com.mateuszholik.data.extensions

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

internal fun String.toLocalDate(): LocalDate {
    val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    return LocalDate.parse(this, dateTimeFormatter)
}

internal fun String.toLocalDateTime(): LocalDateTime =
    ZonedDateTime.parse(this)
        .withZoneSameInstant(ZoneId.systemDefault())
        .toLocalDateTime()
