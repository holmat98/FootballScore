package com.mateuszholik.network.extensions

import com.mateuszholik.network.models.ResultApi
import retrofit2.Response

internal fun <T> Response<T>.toResultApi(): ResultApi<T> =
    if (isSuccessful) {
        body()?.let {
            ResultApi.Success(it)
        } ?: ResultApi.EmptyBody()
    } else {
        ResultApi.Error(code = code(), message = message())
    }
