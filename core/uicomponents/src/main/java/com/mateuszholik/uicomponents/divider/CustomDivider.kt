package com.mateuszholik.uicomponents.divider

import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun CustomDivider() {
    Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
}
