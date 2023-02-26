package com.mateuszholik.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Size(
    val width: Dp,
    val height: Dp
)

@Immutable
data class Sizing(
    val tinyImage: Size = Size(20.dp, 15.dp),
    val smallImage: Size = Size(50.dp, 30.dp)
)

val LocalSizing = staticCompositionLocalOf { Sizing() }

val MaterialTheme.sizing: Sizing
    @Composable
    @ReadOnlyComposable
    get() = LocalSizing.current
