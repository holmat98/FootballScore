package com.mateuszholik.footballscore.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.footballscore.navigation.MainNavigation
import com.mateuszholik.footballscore.navigation.MainNavigation.mainNavigationGraph
import com.mateuszholik.footballscore.navigation.bottomnav.BottomNavItem
import com.mateuszholik.footballscore.navigation.bottomnav.toBottomNavItems
import com.mateuszholik.uicomponents.bottomnavigation.BottomNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FootballScoreTheme(dynamicColors = false) {
                val systemUiController = rememberSystemUiController()
                val navController = rememberNavController()

                systemUiController.setStatusBarColor(
                    color = MaterialTheme.colorScheme.surface,
                    darkIcons = !isSystemInDarkTheme()
                )

                systemUiController.setNavigationBarColor(
                    color = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp),
                    darkIcons = !isSystemInDarkTheme()
                )

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    content = {
                        NavHost(
                            navController = navController,
                            startDestination = MainNavigation.ROOT
                        ) { mainNavigationGraph(navController, it) }
                    },
                    bottomBar = {
                        BottomNavigation(
                            items = BottomNavItem.values().toBottomNavItems(),
                            onItemClick = { navController.navigate(it) },
                            currentRoute = navController.currentDestination?.route
                        )
                    }
                )
            }
        }
    }
}
