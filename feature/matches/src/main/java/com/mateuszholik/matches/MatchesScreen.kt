package com.mateuszholik.matches

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mateuszholik.model.Competition
import com.mateuszholik.model.MatchInfo
import com.mateuszholik.model.UiState
import com.mateuszholik.uicomponents.headers.CompetitionHeader
import com.mateuszholik.uicomponents.loading.Loading
import com.mateuszholik.uicomponents.match.MatchItem

@Composable
fun MatchesScreen(
    viewModel: MatchesViewModel = hiltViewModel(),
) {
    val matchesUiState by viewModel.matches.collectAsStateWithLifecycle()

    when (matchesUiState) {
        is UiState.Loading -> Loading()
        is UiState.Success -> Matches(
            (matchesUiState as UiState.Success<Map<Competition, List<MatchInfo>>>).data
        )
        is UiState.Error -> Text(
            text = "${(matchesUiState as UiState.Error<Map<Competition, List<MatchInfo>>>).errorType}"
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Matches(result: Map<Competition, List<MatchInfo>>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        result.forEach { (competition, matches) ->
            stickyHeader {
                CompetitionHeader(competition = competition)
            }
            itemsIndexed(items = matches) {index, matchInfo ->
                MatchItem(matchInfo = matchInfo)
                if (index < matches.lastIndex) {
                    Divider(color = MaterialTheme.colorScheme.outline)
                }
            }
        }
    }
}
