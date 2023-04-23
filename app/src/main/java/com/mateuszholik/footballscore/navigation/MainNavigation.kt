package com.mateuszholik.footballscore.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.mateuszholik.matchdetails.MatchDetailsScreen
import com.mateuszholik.matchdetails.MatchDetailsViewModel.Companion.MATCH_ID_ARGUMENT
import com.mateuszholik.footballscore.contract.LeagueDetailsContract
import com.mateuszholik.footballscore.ui.installmodule.ModuleInstallationScreen
import com.mateuszholik.footballscore.ui.installmodule.ModuleInstallationViewModel.Companion.MODULE_NAME_ARGUMENT
import com.mateuszholik.matches.MatchesScreen

object MainNavigation {
    const val ROOT = "Main"
    private const val MATCH_LIST = "$ROOT/MATCH_LIST"
    private const val MATCH_DETAILS = "$ROOT/MATCH_DETAILS"
    private const val MODULE_INSTALLATION = "$ROOT/MODULE_INSTALLATION"
    private const val LEAGUE_DETAILS = "$ROOT/LEAGUE_DETAILS"

    const val LEAGUE_ID_ARGUMENT = "leagueId"

    fun NavGraphBuilder.mainNavigationGraph(navController: NavController): Unit =
        navigation(startDestination = MATCH_LIST, route = ROOT) {
            matchesList(navController)
            matchDetails(navController)
            leagueDetailsModuleInstallation(navController)
            leagueDetails(navController)
        }

    private fun NavGraphBuilder.matchesList(navController: NavController): Unit =
        composable(MATCH_LIST) {
            MatchesScreen(
                onMatchClicked = { navController.navigateToMatchDetails(it) },
                onCompetitionClicked = {
                    navController.navigateToLeagueDetailsModuleInstallation(it)
                }
            )
        }

    private fun NavGraphBuilder.matchDetails(navController: NavController): Unit =
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

    private fun NavGraphBuilder.leagueDetailsModuleInstallation(navController: NavController): Unit =
        composable(
            route = "$MODULE_INSTALLATION/$MODULE_NAME_ARGUMENT={$MODULE_NAME_ARGUMENT}/$LEAGUE_ID_ARGUMENT={$LEAGUE_ID_ARGUMENT}",
            arguments = listOf(
                navArgument(MODULE_NAME_ARGUMENT) { type = NavType.StringType },
                navArgument(LEAGUE_ID_ARGUMENT) { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val leagueId = backStackEntry.arguments?.getInt(LEAGUE_ID_ARGUMENT) ?: 0
            ModuleInstallationScreen(
                doOnInstallationFailed = { navController.navigateUp() },
                doOnInstallationSucceeded = { navController.navigateToLeagueDetails(leagueId) }
            )
        }

    private fun NavGraphBuilder.leagueDetails(navController: NavController): Unit =
        composable(
            route = "$LEAGUE_DETAILS/$LEAGUE_ID_ARGUMENT={$LEAGUE_ID_ARGUMENT}",
            arguments = listOf(
                navArgument(LEAGUE_ID_ARGUMENT) { type = NavType.IntType }
            )
        ) { navBackEntry ->
            val leagueId = navBackEntry.arguments?.getInt(LEAGUE_ID_ARGUMENT) ?: 0

            LeagueDetailsContract.getInstance().DisplayLeagueDetails(
                leagueId = leagueId,
                onBackPressed = { navController.navigateUp() }
            )
        }

    private fun NavController.navigateToMatchDetails(matchId: Int) =
        navigate("$MATCH_DETAILS/$MATCH_ID_ARGUMENT=$matchId")

    private fun NavController.navigateToLeagueDetailsModuleInstallation(leagueId: Int) =
        navigate("$MODULE_INSTALLATION/$MODULE_NAME_ARGUMENT=leaguedetails/$LEAGUE_ID_ARGUMENT=$leagueId")

    private fun NavController.navigateToLeagueDetails(leagueId: Int) =
        navigate("$LEAGUE_DETAILS/$LEAGUE_ID_ARGUMENT=$leagueId") {
            popUpTo(currentBackStackEntry?.destination?.route.orEmpty()) { inclusive = true }
        }
}
