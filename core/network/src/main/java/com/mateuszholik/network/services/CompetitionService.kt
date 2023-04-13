package com.mateuszholik.network.services

import com.mateuszholik.network.models.CompetitionDetailsApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

internal interface CompetitionService {

    @GET("v4/competitions/{id}")
    suspend fun getCompetitionDetails(@Path("id") id: Int): Response<CompetitionDetailsApi>

}
