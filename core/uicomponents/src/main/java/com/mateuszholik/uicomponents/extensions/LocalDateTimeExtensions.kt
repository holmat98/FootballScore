package com.mateuszholik.uicomponents.extensions

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

internal fun LocalDateTime.asHourString(): String {
    val dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    return this.format(dateTimeFormatter)
}

internal fun LocalDateTime.asFullDateString(): String {
    val dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

    return this.format(dateTimeFormatter)
}

internal fun LocalDateTime.asFullDateTimeString(): String {
    val dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")

    return this.format(dateTimeFormatter)
}
