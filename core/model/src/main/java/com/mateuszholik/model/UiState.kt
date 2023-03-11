package com.mateuszholik.model

sealed class UiState<T> {

    class Loading<T> : UiState<T>()

    data class Success<T>(val data: T) : UiState<T>()

    data class Error<T>(val errorType: ErrorType): UiState<T>()
}
