package com.mateuszholik.uicomponents.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.stringResource
import com.mateuszholik.designsystem.R
import java.time.Month

internal val Month.toText
    @Composable
    @ReadOnlyComposable
    get() = when(this) {
        Month.JANUARY -> stringResource(R.string.calendar_january)
        Month.FEBRUARY -> stringResource(R.string.calendar_february)
        Month.MARCH -> stringResource(R.string.calendar_march)
        Month.APRIL -> stringResource(R.string.calendar_april)
        Month.MAY -> stringResource(R.string.calendar_may)
        Month.JUNE -> stringResource(R.string.calendar_june)
        Month.JULY -> stringResource(R.string.calendar_july)
        Month.AUGUST -> stringResource(R.string.calendar_august)
        Month.SEPTEMBER -> stringResource(R.string.calendar_september)
        Month.OCTOBER -> stringResource(R.string.calendar_october)
        Month.NOVEMBER -> stringResource(R.string.calendar_november)
        Month.DECEMBER -> stringResource(R.string.calendar_december)
    }
