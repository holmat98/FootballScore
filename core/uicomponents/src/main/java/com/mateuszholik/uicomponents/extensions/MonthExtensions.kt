package com.mateuszholik.uicomponents.extensions

import java.time.Month
import java.time.format.TextStyle
import java.util.Locale

internal fun Month.getShortName(): String =
    this.getDisplayName(TextStyle.SHORT, Locale.getDefault())
