package com.mateuszholik.model

sealed class Result<T> {

    data class Success<T>(val data: T) : Result<T>()
    data class Error(val errorType: ErrorType) : Result<Nothing>()
}
