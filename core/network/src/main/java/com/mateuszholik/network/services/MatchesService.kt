package com.mateuszholik.network.services

import com.mateuszholik.network.models.Head2HeadApi
import com.mateuszholik.network.models.MatchApi
import com.mateuszholik.network.models.MatchesApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface MatchesService {

    @GET("/matches")
    suspend fun getMatchesForDateRange(
        @Query("dateFrom") dateFrom: String,
        @Query("dateTo") dateTo: String
    ): Response<MatchesApi>

    @GET("/matches/{id}")
    suspend fun getMatch(@Path("id") id: Int): Response<MatchApi>

    @GET("/matches/{id}/head2head")
    suspend fun getHead2HeadForMatch(@Path("id") id: Int): Response<Head2HeadApi>
}
