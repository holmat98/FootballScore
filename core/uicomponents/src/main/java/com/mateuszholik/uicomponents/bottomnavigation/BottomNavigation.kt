package com.mateuszholik.uicomponents.bottomnavigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.designsystem.R
import com.mateuszholik.uicomponents.bottomnavigation.model.BottomNavItem

@Composable
fun BottomNavigation(
    items: List<BottomNavItem>,
    onItemClick: (String) -> Unit,
    currentRoute: String?,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    itemColors: NavigationBarItemColors = NavigationBarItemDefaults.colors(
        selectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
        selectedTextColor = MaterialTheme.colorScheme.onSurface,
        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
    ),
) {
    NavigationBar(
        modifier = modifier,
        containerColor = backgroundColor
    ) {
        items.forEach { navItem ->
            NavigationBarItem(
                selected = navItem.route == currentRoute,
                onClick = { onItemClick(navItem.route) },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = stringResource(navItem.text)
                    )
                },
                label = {
                    Text(text = stringResource(navItem.text))
                },
                colors = itemColors
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    FootballScoreTheme {
        BottomNavigation(
            modifier = Modifier.fillMaxWidth(),
            items = listOf(
                BottomNavItem(
                    route = "home",
                    icon = Icons.Filled.Home,
                    text = R.string.bottom_nav_home
                ),
                BottomNavItem(
                    route = "favorite",
                    icon = Icons.Filled.Favorite,
                    text = R.string.bottom_nav_favorite
                )
            ),
            currentRoute = "favorite",
            onItemClick = {}
        )
    }
}