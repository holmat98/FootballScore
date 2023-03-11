package com.mateuszholik.uicomponents.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.stringResource
import com.mateuszholik.designsystem.R
import java.time.DayOfWeek

internal val DayOfWeek.toText: String
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        DayOfWeek.MONDAY -> stringResource(R.string.calendar_monday)
        DayOfWeek.TUESDAY -> stringResource(R.string.calendar_tuesday)
        DayOfWeek.WEDNESDAY -> stringResource(R.string.calendar_wednesday)
        DayOfWeek.THURSDAY -> stringResource(R.string.calendar_thursday)
        DayOfWeek.FRIDAY -> stringResource(R.string.calendar_friday)
        DayOfWeek.SATURDAY -> stringResource(R.string.calendar_saturday)
        DayOfWeek.SUNDAY -> stringResource(R.string.calendar_sunday)
    }
