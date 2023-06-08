package com.mateuszholik.footballscore.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.mateuszholik.designsystem.R
import com.mateuszholik.matchdetails.MatchDetailsScreen
import com.mateuszholik.matchdetails.MatchDetailsViewModel.Companion.MATCH_ID_ARGUMENT
import com.mateuszholik.footballscore.contract.LeagueDetailsContract
import com.mateuszholik.footballscore.ui.installmodule.ModuleInstallationScreen
import com.mateuszholik.footballscore.ui.installmodule.ModuleInstallationViewModel.Companion.MODULE_NAME_ARGUMENT
import com.mateuszholik.matches.MatchesScreen
import com.mateuszholik.uicomponents.bottomnavigation.model.BottomNavItem
import com.mateuszholik.watchedmatches.WatchedMatchesScreen

object MainNavigation {
    const val ROOT = "Main"
    private const val MATCH_LIST = "$ROOT/MATCH_LIST"
    private const val MATCH_DETAILS = "$ROOT/MATCH_DETAILS"
    private const val MODULE_INSTALLATION = "$ROOT/MODULE_INSTALLATION"
    private const val LEAGUE_DETAILS = "$ROOT/LEAGUE_DETAILS"
    private const val WATCHED_MATCHES = "$ROOT/WATCHED_MATCHES"

    private const val LEAGUE_ID_ARGUMENT = "leagueId"

    internal val BOTTOM_NAV_ITEMS = listOf(
        BottomNavItem(
            route = MATCH_LIST,
            text = R.string.bottom_nav_home,
            icon = Icons.Filled.Home
        ),
        BottomNavItem(
            route = WATCHED_MATCHES,
            text = R.string.bottom_nav_favorite,
            icon = Icons.Filled.Favorite
        )
    )

    fun NavGraphBuilder.mainNavigationGraph(
        navController: NavController,
        paddingValues: PaddingValues,
    ): Unit =
        navigation(startDestination = MATCH_LIST, route = ROOT) {
            matchesList(navController, paddingValues)
            matchDetails(navController, paddingValues)
            leagueDetailsModuleInstallation(navController, paddingValues)
            leagueDetails(navController, paddingValues)
            watchedMatches(navController, paddingValues)
        }

    private fun NavGraphBuilder.matchesList(
        navController: NavController,
        paddingValues: PaddingValues,
    ): Unit =
        composable(MATCH_LIST) {
            MatchesScreen(
                modifier = Modifier.padding(paddingValues),
                onMatchClicked = { navController.navigateToMatchDetails(it) },
                onCompetitionClicked = {
                    navController.navigateToLeagueDetailsModuleInstallation(it)
                }
            )
        }

    private fun NavGraphBuilder.matchDetails(
        navController: NavController,
        paddingValues: PaddingValues,
    ): Unit =
        composable(
            route = "$MATCH_DETAILS/$MATCH_ID_ARGUMENT={$MATCH_ID_ARGUMENT}",
            arguments = listOf(
                navArgument(MATCH_ID_ARGUMENT) { type = NavType.IntType }
            )
        ) {
            MatchDetailsScreen(
                modifier = Modifier.padding(paddingValues),
                onBackPressed = { navController.navigateUp() },
                onH2HMatchClicked = { navController.navigateToMatchDetails(it) }
            )
        }

    private fun NavGraphBuilder.leagueDetailsModuleInstallation(
        navController: NavController,
        paddingValues: PaddingValues,
    ): Unit =
        composable(
            route = "$MODULE_INSTALLATION/$MODULE_NAME_ARGUMENT={$MODULE_NAME_ARGUMENT}/$LEAGUE_ID_ARGUMENT={$LEAGUE_ID_ARGUMENT}",
            arguments = listOf(
                navArgument(MODULE_NAME_ARGUMENT) { type = NavType.StringType },
                navArgument(LEAGUE_ID_ARGUMENT) { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val leagueId = backStackEntry.arguments?.getInt(LEAGUE_ID_ARGUMENT) ?: 0

            ModuleInstallationScreen(
                modifier = Modifier.padding(paddingValues),
                doOnInstallationFailed = { navController.navigateUp() },
                doOnInstallationSucceeded = { navController.navigateToLeagueDetails(leagueId) }
            )
        }

    private fun NavGraphBuilder.leagueDetails(
        navController: NavController,
        paddingValues: PaddingValues,
    ): Unit =
        composable(
            route = "$LEAGUE_DETAILS/$LEAGUE_ID_ARGUMENT={$LEAGUE_ID_ARGUMENT}",
            arguments = listOf(
                navArgument(LEAGUE_ID_ARGUMENT) { type = NavType.IntType }
            )
        ) { navBackEntry ->
            val leagueId = navBackEntry.arguments?.getInt(LEAGUE_ID_ARGUMENT) ?: 0

            LeagueDetailsContract.getInstance().DisplayLeagueDetails(
                modifier = Modifier.padding(paddingValues),
                leagueId = leagueId,
                onBackPressed = { navController.navigateUp() }
            )
        }

    private fun NavGraphBuilder.watchedMatches(
        navController: NavController,
        paddingValues: PaddingValues,
    ): Unit =
        composable(route = WATCHED_MATCHES) {
            WatchedMatchesScreen(
                modifier = Modifier.padding(paddingValues),
                onMatchClicked = { navController.navigateToMatchDetails(it) },
                onCompetitionClicked = {
                    navController.navigateToLeagueDetailsModuleInstallation(it)
                }
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

    internal fun NavController.navigateToBottomNavItem(bottomNavRoute: String) =
        navigate(bottomNavRoute) {
            launchSingleTop = true
            popUpTo(ROOT) { inclusive = true }
        }
}
