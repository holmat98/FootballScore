package com.mateuszholik.watchedmatches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mateuszholik.common.extensions.toUiState
import com.mateuszholik.common.providers.DispatchersProvider
import com.mateuszholik.domain.usecases.DeleteWatchedGameUseCase
import com.mateuszholik.domain.usecases.GetWatchedGamesUseCase
import com.mateuszholik.model.ErrorType
import com.mateuszholik.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class WatchedMatchesViewModel @Inject constructor(
    getWatchedGamesUseCase: GetWatchedGamesUseCase,
    private val deleteWatchedGameUseCase: DeleteWatchedGameUseCase,
    private val dispatchersProvider: DispatchersProvider,
) : ViewModel() {

    val watchedMatchesUiState = getWatchedGamesUseCase()
        .map { it.toUiState() }
        .catch {
            Timber.e(it, "Error while getting watched games")
            emit(UiState.Error(ErrorType.UNKNOWN))
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = UiState.Loading()
        )

    fun removeWatchedMatch(matchId: Int) {
        viewModelScope.launch(dispatchersProvider.io) {
            deleteWatchedGameUseCase(matchId)
        }
    }
}
