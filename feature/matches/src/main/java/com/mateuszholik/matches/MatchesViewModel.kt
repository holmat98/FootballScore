package com.mateuszholik.matches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mateuszholik.common.providers.CurrentDateProvider
import com.mateuszholik.common.providers.DateRangeProvider
import com.mateuszholik.common.providers.DispatchersProvider
import com.mateuszholik.domain.usecases.DeleteWatchedGameUseCase
import com.mateuszholik.domain.usecases.GetMatchesForDateUseCase
import com.mateuszholik.domain.usecases.GetWatchedGamesIdsUseCase
import com.mateuszholik.domain.usecases.InsertWatchedGameUseCase
import com.mateuszholik.matches.model.CombinedMatchesInfo
import com.mateuszholik.model.Competition
import com.mateuszholik.model.ErrorType
import com.mateuszholik.model.MatchInfo
import com.mateuszholik.model.Result
import com.mateuszholik.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MatchesViewModel @Inject constructor(
    private val insertWatchedGameUseCase: InsertWatchedGameUseCase,
    private val deleteWatchedGameUseCase: DeleteWatchedGameUseCase,
    private val dispatchersProvider: DispatchersProvider,
    getMatchesForDateUseCase: GetMatchesForDateUseCase,
    getWatchedGamesIdsUseCase: GetWatchedGamesIdsUseCase,
    currentDateProvider: CurrentDateProvider,
    dateRangeProvider: DateRangeProvider,
) : ViewModel() {

    val days = dateRangeProvider.provide(
        startingDate = currentDateProvider.provide().minusDays(HALF_NUM_OF_DAYS.toLong()),
        numOfDays = NUM_OF_DAYS
    )

    private val _currentDay = MutableStateFlow(currentDateProvider.provide())
    val currentDay: StateFlow<LocalDate>
        get() = _currentDay

    @OptIn(FlowPreview::class)
    val matches = _currentDay.flatMapConcat {
        getMatchesForDateUseCase(it)
    }.combine(getWatchedGamesIdsUseCase()) { matchesResult, watchedMatchesIdsResult ->
        handleResult(
            matchesResult = matchesResult,
            watchedMatchesIdsResult = watchedMatchesIdsResult
        )
    }.catch {
        Timber.e(it, "Error occurred while getting the list of matches")
        emit(UiState.Error(ErrorType.UNKNOWN))
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = UiState.Loading(),
    )

    private fun handleResult(
        matchesResult: Result<Map<Competition, List<MatchInfo>>>,
        watchedMatchesIdsResult: Result<List<Int>>,
    ): UiState<CombinedMatchesInfo> =
        when {
            matchesResult is Result.Success && watchedMatchesIdsResult is Result.Success ->
                UiState.Success(
                    CombinedMatchesInfo(
                        matches = matchesResult.data,
                        watchedMatchesIds = watchedMatchesIdsResult.data
                    )
                )
            matchesResult is Result.Success -> UiState.Success(
                CombinedMatchesInfo(matches = matchesResult.data)
            )
            matchesResult is Result.Error -> UiState.Error(matchesResult.errorType)
            else -> UiState.Error(ErrorType.UNKNOWN)
        }

    fun updateCurrentDate(newCurrentDate: LocalDate) {
        viewModelScope.launch {
            _currentDay.emit(newCurrentDate)
        }
    }

    fun addToWatchedMatches(matchId: Int) {
        viewModelScope.launch(dispatchersProvider.io) {
            insertWatchedGameUseCase(matchId)
        }
    }

    fun deleteFromWatchedMatches(matchId: Int) {
        viewModelScope.launch(dispatchersProvider.io) {
            deleteWatchedGameUseCase(matchId)
        }
    }

    private companion object {
        const val NUM_OF_DAYS = 10
        const val HALF_NUM_OF_DAYS = NUM_OF_DAYS / 2
    }
}
