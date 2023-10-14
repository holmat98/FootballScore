package com.mateuszholik.leaguedetails

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.leaguedetails.models.Page
import com.mateuszholik.model.CombinedCompetitionDetails
import com.mateuszholik.model.CompetitionType
import com.mateuszholik.model.UiState
import com.mateuszholik.uicomponents.buttons.SelectableButton
import com.mateuszholik.uicomponents.headers.CompetitionHeader
import com.mateuszholik.uicomponents.info.ErrorInfo
import com.mateuszholik.uicomponents.loading.Loading
import com.mateuszholik.uicomponents.scaffold.CustomScaffold
import com.mateuszholik.uicomponents.utils.PreviewConstants.AREA
import com.mateuszholik.uicomponents.utils.PreviewConstants.COMPETITION_STANDINGS_DETAILS
import com.mateuszholik.uicomponents.utils.PreviewConstants.SCORER
import com.mateuszholik.uicomponents.utils.PreviewConstants.SEASON

@Composable
fun LeagueDetailsScreen(
    modifier: Modifier,
    onBackPressed: () -> Unit,
    viewModel: LeagueDetailsViewModel,
) {
    val combinedCompetitionDetailsUiState by viewModel.combinedCompetitionDetails.collectAsStateWithLifecycle()
    var topAppBarText by remember { mutableStateOf("") }

    CustomScaffold(
        modifier = modifier,
        title = { Text(text = topAppBarText) },
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
            }
        }
    ) {
        when (combinedCompetitionDetailsUiState) {
            is UiState.Error ->
                ErrorInfo(
                    (combinedCompetitionDetailsUiState as UiState.Error<CombinedCompetitionDetails>).errorType
                )

            is UiState.Loading -> Loading()
            is UiState.Success -> {
                val combinedCompetitionDetails =
                    (combinedCompetitionDetailsUiState as UiState.Success<CombinedCompetitionDetails>).data
                LaunchedEffect(Unit) {
                    topAppBarText = combinedCompetitionDetails.name
                }
                Content(
                    paddingValues = it,
                    combinedCompetitionDetails = combinedCompetitionDetails
                )
            }
        }
    }
}

@Composable
private fun Content(
    combinedCompetitionDetails: CombinedCompetitionDetails,
    paddingValues: PaddingValues,
) {
    var currentPage by remember { mutableStateOf(Page.COMPETITION_TABLE) }
    var currentTable by remember { mutableStateOf(0) }

    LazyColumn(modifier = Modifier.padding(paddingValues)) {
        item {
            with(combinedCompetitionDetails) {
                CompetitionHeader(
                    competitionType = type,
                    emblem = emblem,
                    name = name,
                    countryFlag = area.flag,
                    countryName = area.name
                )
            }
        }

        item {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(vertical = MaterialTheme.spacing.small)
            ) {
                items(items = Page.values().toList()) {
                    SelectableButton(
                        text = stringResource(it.textResId),
                        isSelected = it == currentPage,
                        onClick = { currentPage = it }
                    )
                }
            }
        }
        when (currentPage) {
            Page.COMPETITION_TABLE -> leagueTable(
                tables = combinedCompetitionDetails.standingsDetails,
                currentTable = currentTable,
                onTableTypeClicked = { currentTable = it }
            )
            Page.TOP_SCORERS -> topScorers(topScorers = combinedCompetitionDetails.topScorers)
            Page.WINNERS -> winners(seasons = combinedCompetitionDetails.seasons)
        }
    }
}

@Preview(device = Devices.PIXEL_4)
@Composable
private fun Preview() {
    FootballScoreTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Content(
                combinedCompetitionDetails = CombinedCompetitionDetails(
                    area = AREA,
                    id = 1,
                    name = "League",
                    code = "",
                    type = CompetitionType.LEAGUE,
                    emblem = "",
                    currentSeason = SEASON,
                    seasons = listOf(SEASON),
                    standingsDetails = listOf(COMPETITION_STANDINGS_DETAILS),
                    topScorers = listOf(SCORER)
                ),
                paddingValues = PaddingValues(MaterialTheme.spacing.none)
            )
        }
    }
}
