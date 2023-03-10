package com.example.matchdetails

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mateuszholik.designsystem.R
import com.mateuszholik.model.CompetitionType
import com.mateuszholik.model.MatchInfo
import com.mateuszholik.model.UiState
import com.mateuszholik.uicomponents.headers.CompetitionHeader
import com.mateuszholik.uicomponents.headers.MatchScoreHeader
import com.mateuszholik.uicomponents.headers.SmallImageHeader
import com.mateuszholik.uicomponents.headers.SmallMatchScoreHeader
import com.mateuszholik.uicomponents.headers.SmallTextHeader
import com.mateuszholik.uicomponents.info.ErrorInfo
import com.mateuszholik.uicomponents.loading.Loading
import com.mateuszholik.uicomponents.match.H2HMatch
import com.mateuszholik.uicomponents.referee.RefereeItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchDetailsScreen(
    onBackPressed: () -> Unit,
    onH2HMatchClicked: (matchId: Int) -> Unit,
    viewModel: MatchDetailsViewModel = hiltViewModel(),
) {
    val matchDetails by viewModel.matchDetails.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                    }
                },
                title = { Text(text = stringResource(R.string.app_name)) },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    scrolledContainerColor = MaterialTheme.colorScheme.onSurface,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    actionIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                ),
            )
        },
        content = { paddingValues ->
            when (matchDetails) {
                is UiState.Loading -> Loading()
                is UiState.Success -> Content(
                    modifier = Modifier.padding(
                        top = paddingValues.calculateTopPadding(),
                        bottom = paddingValues.calculateBottomPadding()
                    ),
                    data = (matchDetails as UiState.Success<MatchDetailsViewModel.MatchDetails>).data,
                    onH2HMatchClicked = onH2HMatchClicked
                )
                is UiState.Error ->
                    ErrorInfo((matchDetails as UiState.Error<MatchDetailsViewModel.MatchDetails>).errorType)
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Content(
    modifier: Modifier,
    data: MatchDetailsViewModel.MatchDetails,
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
            Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
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

        itemsIndexed(items = data.h2hData.matches) { index, matchInfo ->
            H2HMatch(
                modifier = Modifier.clickable { onH2HMatchClicked(matchInfo.id) },
                matchInfo = matchInfo
            )
            if (index < data.h2hData.matches.lastIndex) {
                Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
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
