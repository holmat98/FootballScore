package com.mateuszholik.matchdetails

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.mateuszholik.designsystem.R
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.matchdetails.model.MatchDetails
import com.mateuszholik.model.CompetitionType
import com.mateuszholik.model.MatchInfo
import com.mateuszholik.model.UiState
import com.mateuszholik.uicomponents.divider.CustomDivider
import com.mateuszholik.uicomponents.head2head.TeamHead2HeadInfo
import com.mateuszholik.uicomponents.headers.CompetitionHeader
import com.mateuszholik.uicomponents.headers.MatchScoreHeader
import com.mateuszholik.uicomponents.headers.SmallImageHeader
import com.mateuszholik.uicomponents.headers.SmallMatchScoreHeader
import com.mateuszholik.uicomponents.headers.SmallTextHeader
import com.mateuszholik.uicomponents.info.ErrorInfo
import com.mateuszholik.uicomponents.loading.Loading
import com.mateuszholik.uicomponents.match.H2HMatch
import com.mateuszholik.uicomponents.referee.RefereeItem
import com.mateuszholik.uicomponents.scaffold.CustomScaffold
import com.mateuszholik.uicomponents.utils.PreviewConstants

@Composable
fun MatchDetailsScreen(
    onBackPressed: () -> Unit,
    onH2HMatchClicked: (matchId: Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MatchDetailsViewModel = hiltViewModel(),
) {
    val matchDetails by viewModel.matchDetails.collectAsStateWithLifecycle()

    CustomScaffold(
        modifier = modifier,
        title = { Text(text = stringResource(R.string.app_name)) },
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
            }
        }
    ) { paddingValues ->
        when (matchDetails) {
            is UiState.Loading -> Loading()
            is UiState.Success -> Content(
                modifier = Modifier.padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding()
                ),
                data = (matchDetails as UiState.Success<MatchDetails>).data,
                onH2HMatchClicked = onH2HMatchClicked
            )
            is UiState.Error ->
                ErrorInfo((matchDetails as UiState.Error<MatchDetails>).errorType)
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Content(
    modifier: Modifier,
    data: MatchDetails,
    onH2HMatchClicked: (matchId: Int) -> Unit,
) {
    val state = rememberLazyListState()
    val matchInfo = MatchInfo(
        id = data.match.id,
        awayTeam = data.match.awayTeam,
        homeTeam = data.match.homeTeam,
        score = data.match.score,
        status = data.match.status,
        utcDate = data.match.utcDate
    )

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        state = state
    ) {
        item { CompetitionHeader(competition = data.match.competition) }

        stickyHeader {
            MatchHeader(
                competitionType = data.match.competition.type,
                matchInfo = matchInfo,
                shouldShowSmallHeader = state.firstVisibleItemIndex == 0
            )
        }

        item { SmallTextHeader(text = stringResource(R.string.match_details_game_info)) }

        items(items = data.match.referees) {
            RefereeItem(referee = it)
            CustomDivider()
        }

        item {
            SmallImageHeader(
                imageRes = R.drawable.ic_stadium,
                text = data.match.venue,
                backgroundColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            )
        }

        item { SmallTextHeader(text = stringResource(R.string.match_details_h2h)) }

        item {
            SmallImageHeader(
                imageRes = R.drawable.ic_ball,
                text = stringResource(
                    R.string.match_details_h2h_num_of_matches,
                    data.h2hData.numberOfMatches
                ),
                backgroundColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            )
            CustomDivider()
        }

        item {
            SmallImageHeader(
                imageRes = R.drawable.ic_ball,
                text = stringResource(
                    R.string.match_details_h2h_num_of_goals,
                    data.h2hData.totalGoals
                ),
                backgroundColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            )
            CustomDivider()
        }

        item {
            TeamHead2HeadInfo(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = MaterialTheme.spacing.small),
                homeTeamCrest = data.match.homeTeam.crest,
                awayTeamCrest = data.match.awayTeam.crest,
                wins = data.h2hData.homeTeam.wins,
                draws = data.h2hData.homeTeam.draws,
                losses = data.h2hData.homeTeam.losses,
            )
        }

        item { SmallTextHeader(text = stringResource(R.string.match_details_last_matches)) }

        itemsIndexed(items = data.h2hData.matches) { index, matchInfo ->
            H2HMatch(
                modifier = Modifier.clickable { onH2HMatchClicked(matchInfo.id) },
                matchInfo = matchInfo
            )
            if (index < data.h2hData.matches.lastIndex) {
                CustomDivider()
            }
        }
    }
}

@Composable
private fun MatchHeader(
    competitionType: CompetitionType,
    matchInfo: MatchInfo,
    shouldShowSmallHeader: Boolean,
) {
    Surface(modifier = Modifier.animateContentSize()) {
        if (shouldShowSmallHeader) {
            MatchScoreHeader(
                competitionType = competitionType,
                matchInfo = matchInfo
            )
        } else {
            SmallMatchScoreHeader(
                competitionType = competitionType,
                matchInfo = matchInfo
            )
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
                data = MatchDetails(
                    match = PreviewConstants.MATCH,
                    h2hData = PreviewConstants.H2H_DATA
                ),
                onH2HMatchClicked = {}
            )
        }
    }
}
