package com.mateuszholik.network.extensions

import com.mateuszholik.network.models.ResultApi
import retrofit2.Response

internal fun <T> Response<T>.toResultApi(): ResultApi<T> =
    if (isSuccessful) {
        body()?.let {
            ResultApi.Success(it)
        } ?: ResultApi.EmptyBody()
    } else {
        ResultApi.Error(
            code = code(),
            message = errorBody()?.string().orEmpty()
        )
    }

internal fun <T, R> Response<T>.toResultApi(map: T.() -> R): ResultApi<R> =
    if (isSuccessful) {
        body()?.let {
            ResultApi.Success(it.map())
        } ?: ResultApi.EmptyBody()
    } else {
        ResultApi.Error(
            code = code(),
            message = errorBody()?.string().orEmpty()
        )
    }
