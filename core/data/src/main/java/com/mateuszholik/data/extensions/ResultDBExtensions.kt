package com.mateuszholik.data.extensions

import com.mateuszholik.database.models.ResultDB
import com.mateuszholik.model.ErrorType
import com.mateuszholik.model.Result

internal fun <T> ResultDB<T>.toResult(): Result<T> =
    when (this) {
        is ResultDB.EmptyBody -> Result.Error(ErrorType.EMPTY_DATA)
        is ResultDB.Success -> Result.Success(data)
    }

internal fun <T, R> ResultDB<T>.toResult(map: T.() -> R): Result<R> =
    when (this) {
        is ResultDB.EmptyBody -> Result.Error(ErrorType.EMPTY_DATA)
        is ResultDB.Success -> Result.Success(data.map())
    }
