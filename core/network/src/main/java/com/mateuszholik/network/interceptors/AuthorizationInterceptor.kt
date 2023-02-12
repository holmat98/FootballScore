package com.mateuszholik.network.interceptors

import com.mateuszholik.network.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

internal class AuthorizationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        request.newBuilder()
            .header(AUTH_TOKEN_NAME, BuildConfig.API_KEY)
            .build()

        return chain.proceed(request)
    }

    private companion object {
        const val AUTH_TOKEN_NAME = "X-Auth-Token"
    }
}
