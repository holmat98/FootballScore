package com.mateuszholik.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Immutable
data class TextSizing(
    val extraSmall: TextUnit = 8.sp,
    val small: TextUnit = 12.sp,
    val normal: TextUnit = 16.sp,
    val large: TextUnit = 24.sp
)

val LocalTextSizing = staticCompositionLocalOf { TextSizing() }

val MaterialTheme.textSizing
    @Composable
    @ReadOnlyComposable
    get() = LocalTextSizing.current
