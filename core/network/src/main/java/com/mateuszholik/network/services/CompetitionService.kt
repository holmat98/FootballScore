package com.mateuszholik.network.services

import com.mateuszholik.network.models.CompetitionDetailsApi
import com.mateuszholik.network.models.ScorersApi
import com.mateuszholik.network.models.StandingsApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

internal interface CompetitionService {

    @GET("v4/competitions/{id}")
    suspend fun getCompetitionDetails(@Path("id") id: Int): Response<CompetitionDetailsApi>

    @GET("v4/competitions/{id}/standings")
    suspend fun getStandingsOfCompetition(@Path("id") id: Int): Response<StandingsApi>

    @GET("v4/competitions/{id}/scorers")
    suspend fun getTopScorersOfCompetition(@Path("id") id: Int): Response<ScorersApi>

}
