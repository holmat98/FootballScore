package com.mateuszholik.watchedmatches

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mateuszholik.designsystem.R
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.model.Competition
import com.mateuszholik.model.MatchInfo
import com.mateuszholik.model.UiState
import com.mateuszholik.uicomponents.divider.CustomDivider
import com.mateuszholik.uicomponents.headers.CompetitionHeader
import com.mateuszholik.uicomponents.info.ErrorInfo
import com.mateuszholik.uicomponents.loading.Loading
import com.mateuszholik.uicomponents.match.MatchItem
import com.mateuszholik.uicomponents.utils.PreviewConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WatchedMatchesScreen(
    onCompetitionClicked: (competitionId: Int) -> Unit,
    onMatchClicked: (matchId: Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: WatchedMatchesViewModel = hiltViewModel(),
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(topAppBarState)

    val watchedMatchesUiState by viewModel.watchedMatchesUiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.watched_matches_title))
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    scrolledContainerColor = MaterialTheme.colorScheme.onSurface,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    actionIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                scrollBehavior = scrollBehavior
            )
        },
        content = { paddingValues ->
            when (watchedMatchesUiState) {
                is UiState.Loading -> Loading()
                is UiState.Success -> Content(
                    modifier = Modifier.padding(
                        top = paddingValues.calculateTopPadding(),
                        bottom = paddingValues.calculateBottomPadding()
                    ),
                    data = (watchedMatchesUiState as UiState.Success<Map<Competition, List<MatchInfo>>>).data,
                    onMatchClicked = onMatchClicked,
                    onCompetitionClicked = onCompetitionClicked,
                    onFavoriteButtonClicked = { matchId -> viewModel.removeWatchedMatch(matchId) }
                )
                is UiState.Error ->
                    ErrorInfo((watchedMatchesUiState as UiState.Error<Map<Competition, List<MatchInfo>>>).errorType)
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Content(
    modifier: Modifier,
    data: Map<Competition, List<MatchInfo>>,
    onMatchClicked: (matchId: Int) -> Unit,
    onCompetitionClicked: (competitionId: Int) -> Unit,
    onFavoriteButtonClicked: (matchId: Int) -> Unit,
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        data.forEach { (competition, matches) ->
            stickyHeader {
                CompetitionHeader(
                    modifier = Modifier.clickable { onCompetitionClicked(competition.id) },
                    competition = competition
                )
            }
            itemsIndexed(
                items = matches,
                key = { _, item -> item.id }
            ) { index, matchInfo ->
                MatchItem(
                    modifier = Modifier.clickable { onMatchClicked(matchInfo.id) },
                    matchInfo = matchInfo,
                    onFavoriteButtonClicked = { onFavoriteButtonClicked(matchInfo.id) },
                    isAddedToFavorites = true
                )
                if (index < matches.lastIndex) {
                    CustomDivider()
                }
            }
        }
    }
}

@Preview(device = Devices.PIXEL_4)
@Composable
private fun Preview() {
    FootballScoreTheme {
        Surface(color = MaterialTheme.colorScheme.surface) {
            Content(
                modifier = Modifier,
                data = mapOf(
                    PreviewConstants.COMPETITION to listOf(
                        PreviewConstants.IN_PLAY_MATCH_INFO,
                        PreviewConstants.FINISHED_MATCH_INFO,
                        PreviewConstants.SCHEDULED_MATCH_INFO
                    ),
                    PreviewConstants.COMPETITION_2 to listOf(
                        PreviewConstants.IN_PLAY_MATCH_INFO,
                        PreviewConstants.SCHEDULED_MATCH_INFO,
                        PreviewConstants.FINISHED_MATCH_INFO
                    ),
                ),
                onMatchClicked = {},
                onCompetitionClicked = {},
                onFavoriteButtonClicked = {}
            )
        }
    }
}
