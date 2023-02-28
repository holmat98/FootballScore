package com.mateuszholik.common.extensions

import com.mateuszholik.model.Result
import com.mateuszholik.model.UiState

fun <T> Result<T>.toUiState(): UiState<T> =
    when (this) {
        is Result.Success -> UiState.Success(data)
        is Result.Error -> UiState.Error(errorType)
    }
