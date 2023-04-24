package com.mateuszholik.leaguedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.mateuszholik.common.extensions.toUiState
import com.mateuszholik.domain.usecases.GetCombinedCompetitionDetailsUseCase
import com.mateuszholik.model.CombinedCompetitionDetails
import com.mateuszholik.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class LeagueDetailsViewModel @Inject constructor(
    getCombinedCompetitionDetailsUseCase: GetCombinedCompetitionDetailsUseCase,
    leagueId: Int
) : ViewModel() {

    val combinedCompetitionDetails: StateFlow<UiState<CombinedCompetitionDetails>> =
        getCombinedCompetitionDetailsUseCase(leagueId)
            .map { it.toUiState() }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(),
                initialValue = UiState.Loading(),
            )
}

@Suppress("UNCHECKED_CAST")
class LeagueDetailsViewModelFactory @Inject constructor(
    private val getCombinedCompetitionDetailsUseCase: GetCombinedCompetitionDetailsUseCase,
    private val leagueId: Int,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        LeagueDetailsViewModel(
            getCombinedCompetitionDetailsUseCase = getCombinedCompetitionDetailsUseCase,
            leagueId = leagueId
        ) as T
}