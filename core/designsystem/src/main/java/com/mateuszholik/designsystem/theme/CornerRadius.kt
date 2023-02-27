package com.mateuszholik.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class CornerRadius(
    val none: Dp = 0.dp,
    val small: Dp = 4.dp,
    val normal: Dp = 8.dp,
    val medium: Dp = 12.dp,
    val large: Dp = 16.dp
)

val LocalCornerRadius = staticCompositionLocalOf { CornerRadius() }

val MaterialTheme.cornerRadius
    @Composable
    @ReadOnlyComposable
    get() = LocalCornerRadius.current
