package com.mateuszholik.matches

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
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
import com.mateuszholik.uicomponents.calendar.Calendar
import com.mateuszholik.uicomponents.dialogs.DatePickerDialog
import com.mateuszholik.uicomponents.divider.CustomDivider
import com.mateuszholik.uicomponents.headers.CompetitionHeader
import com.mateuszholik.uicomponents.info.ErrorInfo
import com.mateuszholik.uicomponents.loading.Loading
import com.mateuszholik.uicomponents.match.MatchItem
import com.mateuszholik.uicomponents.scaffold.CustomScaffold
import com.mateuszholik.uicomponents.utils.PreviewConstants
import java.time.LocalDate

@Composable
fun MatchesScreen(
    onCompetitionClicked: (competitionId: Int) -> Unit,
    onMatchClicked: (matchId: Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MatchesViewModel = hiltViewModel(),
) {

    var shouldShowDatePicker by remember { mutableStateOf(false) }
    val days = remember { viewModel.days }
    val currentDay by viewModel.currentDay.collectAsStateWithLifecycle()
    val matchesUiState by viewModel.matches.collectAsStateWithLifecycle()
    val watchedMatchesIds by viewModel.watchedMatches.collectAsStateWithLifecycle()

    CustomScaffold(
        modifier = modifier,
        title = { Text(text = stringResource(R.string.app_name)) },
        actions = {
            IconButton(onClick = { shouldShowDatePicker = true }) {
                Image(
                    painter = painterResource(R.drawable.ic_calendar),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                )
            }
        }
    ) { paddingValues ->
        when (matchesUiState) {
            is UiState.Loading -> Loading()
            is UiState.Success -> Content(
                modifier = Modifier.padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding()
                ),
                days = days,
                selectedDay = currentDay,
                data = (matchesUiState as UiState.Success<Map<Competition, List<MatchInfo>>>).data,
                watchedMatchesIds = watchedMatchesIds,
                onDaySelected = { viewModel.updateCurrentDate(it) },
                onMatchClicked = onMatchClicked,
                onCompetitionClicked = onCompetitionClicked,
                onFavoriteButtonClicked = { isChecked, matchId ->
                    if (isChecked) {
                        viewModel.addToWatchedMatches(matchId)
                    } else {
                        viewModel.deleteFromWatchedMatches(matchId)
                    }
                }
            )

            is UiState.Error ->
                ErrorInfo((matchesUiState as UiState.Error<Map<Competition, List<MatchInfo>>>).errorType)
        }

        if (shouldShowDatePicker) {
            DatePickerDialog(
                title = stringResource(R.string.dialog_date_picker_title),
                onDateSelected = {
                    viewModel.updateCurrentDate(it)
                },
                onDismissRequest = {
                    shouldShowDatePicker = false
                }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Content(
    modifier: Modifier,
    days: List<LocalDate>,
    selectedDay: LocalDate,
    data: Map<Competition, List<MatchInfo>>,
    watchedMatchesIds: List<Int>,
    onDaySelected: (LocalDate) -> Unit,
    onMatchClicked: (matchId: Int) -> Unit,
    onCompetitionClicked: (competitionId: Int) -> Unit,
    onFavoriteButtonClicked: (isChecked: Boolean, matchId: Int) -> Unit,
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        item {
            Calendar(
                days = days,
                selectedDay = selectedDay,
                onDaySelected = onDaySelected
            )
        }

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
                    onFavoriteButtonClicked = { onFavoriteButtonClicked(it, matchInfo.id) },
                    isAddedToFavorites = matchInfo.id in watchedMatchesIds
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
                days = PreviewConstants.DAYS,
                selectedDay = PreviewConstants.SELECTED_DAY,
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
                watchedMatchesIds = listOf(1),
                onDaySelected = {},
                onMatchClicked = {},
                onCompetitionClicked = {},
                onFavoriteButtonClicked = { _, _ -> }
            )
        }
    }
}
