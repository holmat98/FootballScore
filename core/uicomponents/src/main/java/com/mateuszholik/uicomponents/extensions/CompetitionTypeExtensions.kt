package com.mateuszholik.uicomponents.extensions

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import com.mateuszholik.model.CompetitionType

internal val CompetitionType.backgroundColorForCompetition: Color
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        CompetitionType.CUP -> MaterialTheme.colorScheme.secondary
        CompetitionType.LEAGUE -> MaterialTheme.colorScheme.secondaryContainer
        CompetitionType.SUPER_CUP -> MaterialTheme.colorScheme.tertiary
        CompetitionType.PLAYOFFS -> MaterialTheme.colorScheme.tertiaryContainer
    }

internal val CompetitionType.textColorForCompetition: Color
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        CompetitionType.CUP -> MaterialTheme.colorScheme.onSecondary
        CompetitionType.LEAGUE -> MaterialTheme.colorScheme.onSecondaryContainer
        CompetitionType.SUPER_CUP -> MaterialTheme.colorScheme.onTertiary
        CompetitionType.PLAYOFFS -> MaterialTheme.colorScheme.onTertiaryContainer
    }
