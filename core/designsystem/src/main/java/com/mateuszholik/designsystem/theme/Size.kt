package com.mateuszholik.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Sizing(
    val tiny: Dp = 8.dp,
    val extraSmall: Dp = 16.dp,
    val small: Dp = 24.dp,
    val medium: Dp = 36.dp,
    val normal: Dp = 48.dp,
    val large: Dp = 64.dp,
    val extraLarge: Dp = 100.dp
)

val LocalSizing = staticCompositionLocalOf { Sizing() }

val MaterialTheme.sizing: Sizing
    @Composable
    @ReadOnlyComposable
    get() = LocalSizing.current
