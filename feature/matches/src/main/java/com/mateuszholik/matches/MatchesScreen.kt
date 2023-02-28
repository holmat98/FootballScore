package com.mateuszholik.matches

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mateuszholik.model.Competition
import com.mateuszholik.model.MatchInfo
import com.mateuszholik.model.UiState
import com.mateuszholik.uicomponents.calendar.Calendar
import com.mateuszholik.uicomponents.headers.CompetitionHeader
import com.mateuszholik.uicomponents.info.ErrorInfo
import com.mateuszholik.uicomponents.loading.Loading
import com.mateuszholik.uicomponents.match.MatchItem
import java.time.LocalDate

@Composable
fun MatchesScreen(
    viewModel: MatchesViewModel = hiltViewModel(),
) {
    val days by viewModel.days
    val currentDay by viewModel.currentDay.collectAsStateWithLifecycle()
    val matchesUiState by viewModel.matches.collectAsStateWithLifecycle()

    when (matchesUiState) {
        is UiState.Loading -> Loading()
        is UiState.Success -> Content(
            days = days,
            selectedDay = currentDay,
            data = (matchesUiState as UiState.Success<Map<Competition, List<MatchInfo>>>).data,
            onDaySelected = { viewModel.updateCurrentDate(it) }
        )
        is UiState.Error ->
            ErrorInfo((matchesUiState as UiState.Error<Map<Competition, List<MatchInfo>>>).errorType)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Content(
    days: List<LocalDate>,
    selectedDay: LocalDate,
    data: Map<Competition, List<MatchInfo>>,
    onDaySelected: (LocalDate) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Calendar(
                days = days,
                selectedDay = selectedDay,
                onDaySelected = onDaySelected
            )
        }

        data.forEach { (competition, matches) ->
            stickyHeader {
                CompetitionHeader(competition = competition)
            }
            itemsIndexed(items = matches) { index, matchInfo ->
                MatchItem(matchInfo = matchInfo)
                if (index < matches.lastIndex) {
                    Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
                }
            }
        }
    }
}
