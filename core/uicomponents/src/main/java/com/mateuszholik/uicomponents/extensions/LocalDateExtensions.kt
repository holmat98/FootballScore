package com.mateuszholik.uicomponents.extensions

import java.time.LocalDate
import java.time.format.DateTimeFormatter

internal fun LocalDate.asString(): String {

    val dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM")

    return this.format(dateTimeFormatter)
}
