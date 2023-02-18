package com.mateuszholik.data.extensions

import com.mateuszholik.model.ErrorType
import com.mateuszholik.network.models.ResultApi
import com.mateuszholik.model.Result

internal fun <T, R> ResultApi<T>.toResult(map: T.() -> R): Result<R> =
    when (this) {
        is ResultApi.EmptyBody -> Result.Error(ErrorType.EMPTY_DATA)
        is ResultApi.Error -> Result.Error(code.toErrorType())
        is ResultApi.Success -> Result.Success(data.map())
    }

private fun Int.toErrorType(): ErrorType =
    when (this) {
        in 400..499 -> ErrorType.CLIENT_ERROR
        in 500..599 -> ErrorType.SERVER_ERROR
        else -> ErrorType.UNKNOWN
    }
