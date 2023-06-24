package com.mateuszholik.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mateuszholik.common.extensions.toUiState
import com.mateuszholik.domain.usecases.GetTopSportsNewsUseCase
import com.mateuszholik.model.ArticleSortingOptions
import com.mateuszholik.model.ErrorType
import com.mateuszholik.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    getTopSportsNewsUseCase: GetTopSportsNewsUseCase,
) : ViewModel() {

    private val _sortingOptions = MutableStateFlow(ArticleSortingOptions.PUBLISHED_AT)
    val sortingOptions: StateFlow<ArticleSortingOptions>
        get() = _sortingOptions

    @OptIn(FlowPreview::class)
    val topSportsNewsUiState = sortingOptions.flatMapConcat {
        getTopSportsNewsUseCase(it)
    }
        .map { result ->
            result.toUiState()
        }
        .catch {
            Timber.e(it, "Error occurred while getting the sport news articles")
            emit(UiState.Error(ErrorType.UNKNOWN))
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = UiState.Loading(),
        )

    fun changeSortingOption(sortingOptions: ArticleSortingOptions) {
        viewModelScope.launch {
            _sortingOptions.emit(sortingOptions)
        }
    }
}
