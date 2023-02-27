package com.mateuszholik.matches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mateuszholik.common.extensions.toUiState
import com.mateuszholik.domain.usecases.GetMatchesForDateUseCase
import com.mateuszholik.model.Competition
import com.mateuszholik.model.ErrorType
import com.mateuszholik.model.Match
import com.mateuszholik.model.MatchInfo
import com.mateuszholik.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import timber.log.Timber
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MatchesViewModel @Inject constructor(
    getMatchesForDateUseCase: GetMatchesForDateUseCase,
) : ViewModel() {

    val matches: StateFlow<UiState<Map<Competition, List<MatchInfo>>>> =
        getMatchesForDateUseCase(LocalDate.now())
            .map { result ->
                result.toUiState {
                    this.groupBy { it.competition }
                        .mapValues { map ->
                            map.value.map { it.toMatchInfo() }
                        }
                }
            }
            .catch {
                Timber.e("Error occurred during getting the list of matches", it)
                emit(UiState.Error(ErrorType.UNKNOWN))
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = UiState.Loading(),
                )
}

private fun Match.toMatchInfo(): MatchInfo =
    MatchInfo(
        awayTeam = awayTeam,
        homeTeam = homeTeam,
        score = score,
        status = status,
        utcDate = utcDate
    )
