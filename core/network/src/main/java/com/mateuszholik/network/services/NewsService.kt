package com.mateuszholik.network.services

import com.mateuszholik.network.BuildConfig
import com.mateuszholik.network.models.ArticlesApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

internal interface NewsService {

    @Headers(value = ["X-Api-Key: ${BuildConfig.NEWS_API_KEY}"])
    @GET("/v2/everything")
    suspend fun getTopSportsNews(
        @Query("domains") domains: String,
        @Query("from") fromDate: String,
        @Query("sortBy") sortBy: String
    ): Response<ArticlesApi>
}
