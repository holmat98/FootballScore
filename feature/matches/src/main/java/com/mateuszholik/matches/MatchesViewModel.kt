package com.mateuszholik.matches

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mateuszholik.common.extensions.toUiState
import com.mateuszholik.common.providers.CurrentDateProvider
import com.mateuszholik.common.providers.DateRangeProvider
import com.mateuszholik.domain.usecases.GetMatchesForDateUseCase
import com.mateuszholik.model.ErrorType
import com.mateuszholik.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MatchesViewModel @Inject constructor(
    getMatchesForDateUseCase: GetMatchesForDateUseCase,
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
    val matches = _currentDay.flatMapMerge {
        getMatchesForDateUseCase(it)
    }
        .map { result ->
            result.toUiState()
        }
        .catch {
            Timber.e(it,"Error occurred while getting the list of matches")
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

    private companion object {
        const val NUM_OF_DAYS = 10
        const val HALF_NUM_OF_DAYS = NUM_OF_DAYS / 2
    }
}
