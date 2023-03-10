package com.mateuszholik.footballscore.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.matchdetails.MatchDetailsScreen
import com.mateuszholik.matches.MatchesScreen

object MainNavigation {
    const val ROOT = "Main"
    private const val MATCH_LIST = "$ROOT/MATCH_LIST"
    private const val MATCH_DETAILS = "$ROOT/MATCH_DETAILS"

    private const val MATCH_ID_ARGUMENT = "matchId"

    fun NavGraphBuilder.mainNavigationGraph(navController: NavController): Unit =
        navigation(startDestination = MATCH_LIST, route = ROOT) {
            composable(MATCH_LIST) {
                MatchesScreen(onMatchClicked = { navController.navigateToMatchDetails(it) })
            }
            composable(
                route = "$MATCH_DETAILS/$MATCH_ID_ARGUMENT={$MATCH_ID_ARGUMENT}",
                arguments = listOf(
                    navArgument(MATCH_ID_ARGUMENT) { type = NavType.IntType }
                )
            ) {
                MatchDetailsScreen(
                    onBackPressed = { navController.navigateUp() },
                    onH2HMatchClicked = { navController.navigateToMatchDetails(it) }
                )
            }
        }

    private fun NavController.navigateToMatchDetails(matchId: Int) =
        navigate("$MATCH_DETAILS/$MATCH_ID_ARGUMENT=${matchId}")
}
