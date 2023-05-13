package com.mateuszholik.leaguedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.mateuszholik.common.extensions.toUiState
import com.mateuszholik.domain.usecases.GetCombinedCompetitionDetailsUseCase
import com.mateuszholik.model.CombinedCompetitionDetails
import com.mateuszholik.model.ErrorType
import com.mateuszholik.model.UiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import timber.log.Timber

class LeagueDetailsViewModel(
    getCombinedCompetitionDetailsUseCase: GetCombinedCompetitionDetailsUseCase,
    leagueId: Int
) : ViewModel() {

    val combinedCompetitionDetails: StateFlow<UiState<CombinedCompetitionDetails>> =
        getCombinedCompetitionDetailsUseCase(leagueId)
            .map { it.toUiState() }
            .catch {
                Timber.e(it, "Error while getting combined Competition details")
                emit(UiState.Error(ErrorType.UNKNOWN))
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(),
                initialValue = UiState.Loading(),
            )
}

@Suppress("UNCHECKED_CAST")
class LeagueDetailsViewModelFactory(
    private val getCombinedCompetitionDetailsUseCase: GetCombinedCompetitionDetailsUseCase,
    private val leagueId: Int,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        LeagueDetailsViewModel(
            getCombinedCompetitionDetailsUseCase = getCombinedCompetitionDetailsUseCase,
            leagueId = leagueId
        ) as T
}
