package com.mateuszholik.network.extensions

import java.time.LocalDate
import java.time.format.DateTimeFormatter

internal fun LocalDate.asString(): String {
    val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    return this.format(dateTimeFormatter)
}
