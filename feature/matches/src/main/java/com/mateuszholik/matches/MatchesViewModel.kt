package com.mateuszholik.matches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mateuszholik.common.extensions.toUiState
import com.mateuszholik.domain.usecases.GetMatchesForDateUseCase
import com.mateuszholik.model.Match
import com.mateuszholik.model.MatchInfo
import com.mateuszholik.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MatchesViewModel @Inject constructor(
    getMatchesForDateUseCase: GetMatchesForDateUseCase
): ViewModel() {

    val matches: StateFlow<UiState<List<MatchInfo>>> =
        getMatchesForDateUseCase(LocalDate.now())
            .map { result ->
                result.toUiState {matches ->
                    matches.map {
                        it.toMatchInfo()
                    }
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = UiState.Loading(),

            )
}

private fun Match.toMatchInfo() =
    MatchInfo(
        awayTeam = awayTeam,
        homeTeam = homeTeam,
        score = score,
        status = status,
        utcDate = utcDate
    )
