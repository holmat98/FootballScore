package com.mateuszholik.uicomponents.extensions

import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.Locale

internal fun DayOfWeek.getShortName(): String =
    getDisplayName(TextStyle.SHORT, Locale.getDefault())
