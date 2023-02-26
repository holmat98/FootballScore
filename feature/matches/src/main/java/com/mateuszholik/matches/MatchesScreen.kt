package com.mateuszholik.matches

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mateuszholik.model.MatchInfo
import com.mateuszholik.model.UiState
import com.mateuszholik.uicomponents.match.MatchItem

@Composable
fun MatchesScreen(
    viewModel: MatchesViewModel = hiltViewModel(),
) {
    val matchesUiState by viewModel.matches.collectAsStateWithLifecycle()

    when (matchesUiState) {
        is UiState.Loading -> Text(text = "Loading")
        is UiState.Success -> Matches((matchesUiState as UiState.Success<List<MatchInfo>>).data)
        is UiState.Error -> Text(text = "${(matchesUiState as UiState.Error<List<MatchInfo>>).errorType}")
    }
}

@Composable
private fun Matches(matches: List<MatchInfo>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items = matches) {
            MatchItem(matchInfo = it)
        }
    }
}
