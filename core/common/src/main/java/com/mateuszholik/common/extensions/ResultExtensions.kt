package com.mateuszholik.common.extensions

import com.mateuszholik.model.Result
import com.mateuszholik.model.UiState

fun <T, R> Result<T>.toUiState(mapper: (T) -> R): UiState<R> =
    when (this) {
        is Result.Success -> UiState.Success(mapper(data))
        is Result.Error -> UiState.Error(errorType)
    }
