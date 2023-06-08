package com.mateuszholik.database.models

sealed class ResultDB<T> {

    data class Success<T>(val data: T) : ResultDB<T>()
    class EmptyBody<T> : ResultDB<T>()
}
