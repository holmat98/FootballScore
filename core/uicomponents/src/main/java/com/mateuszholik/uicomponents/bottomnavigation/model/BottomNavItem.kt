package com.mateuszholik.uicomponents.bottomnavigation.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val route: String,
    @StringRes val text: Int,
    val icon: ImageVector,
)
