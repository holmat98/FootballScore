package com.mateuszholik.common.extensions

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun LocalDate.toDateString(): String =
    this.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
