package com.mateuszholik.uicomponents.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.stringResource
import com.mateuszholik.model.Duration
import com.mateuszholik.designsystem.R

@Composable
@ReadOnlyComposable
internal fun Duration.toText(): String {
    return when (this) {
        Duration.REGULAR -> stringResource(R.string.duration_regular)
        Duration.PENALTY_SHOOTOUT -> stringResource(R.string.duration_penalties)
        Duration.EXTRA_TIME -> stringResource(R.string.duration_extra_time)
    }
}
