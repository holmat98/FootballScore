package com.mateuszholik.footballscore.navigation.bottomnav

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.mateuszholik.designsystem.R
import com.mateuszholik.footballscore.navigation.MainNavigation.BOTTOM_NAV_FAVORITE
import com.mateuszholik.footballscore.navigation.MainNavigation.BOTTOM_NAV_HOME
import com.mateuszholik.uicomponents.bottomnavigation.model.BottomNavItem as BottomNavigationItem

internal enum class BottomNavItem(
    val route: String,
    @StringRes val text: Int,
    val icon: ImageVector,
) {

    HOME(
        route = BOTTOM_NAV_HOME,
        text = R.string.bottom_nav_home,
        icon = Icons.Filled.Home
    ),
    FAVORITE(
        route = BOTTOM_NAV_FAVORITE,
        text = R.string.bottom_nav_favorite,
        icon = Icons.Filled.Favorite
    )
}

internal fun Array<BottomNavItem>.toBottomNavItems(): List<BottomNavigationItem> =
    BottomNavItem.values().toList().map {
        BottomNavigationItem(
            route = it.route,
            text = it.text,
            icon = it.icon
        )
    }
