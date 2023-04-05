package com.mateuszholik.matchdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mateuszholik.domain.usecases.GetHead2HeadUseCase
import com.mateuszholik.domain.usecases.GetMatchUseCase
import com.mateuszholik.matchdetails.model.MatchDetails
import com.mateuszholik.model.ErrorType
import com.mateuszholik.model.Head2Head
import com.mateuszholik.model.Match
import com.mateuszholik.model.Result
import com.mateuszholik.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MatchDetailsViewModel @Inject constructor(
    getMatchUseCase: GetMatchUseCase,
    getMatchHead2HeadUseCase: GetHead2HeadUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val matchId: Int = savedStateHandle[MATCH_ID_ARGUMENT] ?: 0

    val matchDetails: StateFlow<UiState<MatchDetails>> =
        getMatchUseCase(matchId).combine(
            getMatchHead2HeadUseCase(matchId)
        ) { matchResult, head2HeadResult ->
            combineMatchDetailsResult(matchResult, head2HeadResult)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = UiState.Loading(),
        )

    private fun combineMatchDetailsResult(
        matchResult: Result<Match>,
        head2HeadResult: Result<Head2Head>,
    ): UiState<MatchDetails> =
        when {
            matchResult is Result.Success && head2HeadResult is Result.Success -> UiState.Success(
                MatchDetails(
                    match = matchResult.data,
                    h2hData = head2HeadResult.data
                )
            )
            matchResult is Result.Error -> UiState.Error(matchResult.errorType)
            head2HeadResult is Result.Error -> UiState.Error(head2HeadResult.errorType)
            else -> UiState.Error(ErrorType.UNKNOWN)
        }

    companion object {
        const val MATCH_ID_ARGUMENT = "matchId"
    }
}
