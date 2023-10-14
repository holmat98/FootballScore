package com.mateuszholik.watchedmatches

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mateuszholik.common.extensions.toDateString
import com.mateuszholik.designsystem.R
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.model.UiState
import com.mateuszholik.model.WatchedMatchesMap
import com.mateuszholik.uicomponents.divider.CustomDivider
import com.mateuszholik.uicomponents.headers.CompetitionHeader
import com.mateuszholik.uicomponents.headers.SmallTextHeader
import com.mateuszholik.uicomponents.info.ErrorInfo
import com.mateuszholik.uicomponents.loading.Loading
import com.mateuszholik.uicomponents.match.MatchItem
import com.mateuszholik.uicomponents.scaffold.CustomScaffold
import com.mateuszholik.uicomponents.utils.PreviewConstants
import java.time.LocalDate

@Composable
fun WatchedMatchesScreen(
    onCompetitionClicked: (competitionId: Int) -> Unit,
    onMatchClicked: (matchId: Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: WatchedMatchesViewModel = hiltViewModel(),
) {
    val watchedMatchesUiState by viewModel.watchedMatchesUiState.collectAsStateWithLifecycle()

    CustomScaffold(
        modifier = modifier,
        title = { Text(text = stringResource(R.string.watched_matches_title)) },
    ) { paddingValues ->
        when (watchedMatchesUiState) {
            is UiState.Loading -> Loading()
            is UiState.Success -> Content(
                modifier = Modifier.padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding()
                ),
                data = (watchedMatchesUiState as UiState.Success<WatchedMatchesMap>).data,
                onMatchClicked = onMatchClicked,
                onCompetitionClicked = onCompetitionClicked,
                onFavoriteButtonClicked = { matchId -> viewModel.removeWatchedMatch(matchId) }
            )
            is UiState.Error ->
                ErrorInfo((watchedMatchesUiState as UiState.Error<WatchedMatchesMap>).errorType)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Content(
    modifier: Modifier,
    data: WatchedMatchesMap,
    onMatchClicked: (matchId: Int) -> Unit,
    onCompetitionClicked: (competitionId: Int) -> Unit,
    onFavoriteButtonClicked: (matchId: Int) -> Unit,
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        data.forEach { (matchDate, matchInfoMap) ->
            stickyHeader {
                SmallTextHeader(text = matchDate.toDateString())
            }

            matchInfoMap.forEach { (competition, matches) ->
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
}

@Preview(device = Devices.PIXEL_4)
@Composable
private fun Preview() {
    FootballScoreTheme {
        Surface(color = MaterialTheme.colorScheme.surface) {
            Content(
                modifier = Modifier,
                data = mapOf(
                    LocalDate.of(2023, 3, 4) to mapOf(
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
                    )
                ),
                onMatchClicked = {},
                onCompetitionClicked = {},
                onFavoriteButtonClicked = {}
            )
        }
    }
}
