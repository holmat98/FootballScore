package com.mateuszholik.common.extensions

import com.mateuszholik.model.Result
import com.mateuszholik.model.UiState

fun <T, R> Result<T>.toUiState(map: T.() -> R): UiState<R> =
    when (this) {
        is Result.Success -> UiState.Success(data.map())
        is Result.Error -> UiState.Error(errorType)
    }
