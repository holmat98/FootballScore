package com.mateuszholik.matches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mateuszholik.common.extensions.toUiState
import com.mateuszholik.common.providers.CurrentDateProvider
import com.mateuszholik.common.providers.DateRangeProvider
import com.mateuszholik.common.providers.DispatchersProvider
import com.mateuszholik.domain.usecases.DeleteWatchedGameUseCase
import com.mateuszholik.domain.usecases.GetMatchesForDateUseCase
import com.mateuszholik.domain.usecases.GetWatchedGamesIdsUseCase
import com.mateuszholik.domain.usecases.InsertWatchedGameUseCase
import com.mateuszholik.model.ErrorType
import com.mateuszholik.model.Result
import com.mateuszholik.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
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

    val watchedMatches = getWatchedGamesIdsUseCase()
        .filter { it is Result.Success }
        .map {
            (it as Result.Success).data
        }.catch {
            Timber.e(it, "Error while getting the list of watched games ids")
            emit(emptyList())
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    @OptIn(FlowPreview::class)
    val matches = _currentDay.flatMapConcat {
        getMatchesForDateUseCase(it)
    }
        .map { result ->
            result.toUiState()
        }
        .catch {
            Timber.e(it, "Error occurred while getting the list of matches")
            emit(UiState.Error(ErrorType.UNKNOWN))
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = UiState.Loading(),
        )

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
