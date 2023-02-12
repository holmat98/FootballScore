package com.mateuszholik.network.models

sealed class ResultApi<T> {

    data class Success<T>(val data: T) : ResultApi<T>()
    class EmptyBody<T> : ResultApi<T>()
    data class Error<T>(val code: Int, val message: String) : ResultApi<T>()
}
