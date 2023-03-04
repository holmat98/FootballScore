package com.mateuszholik.uicomponents.extensions

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

internal fun LocalDateTime.asHourString(): String {
    val dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    return this.format(dateTimeFormatter)
}
