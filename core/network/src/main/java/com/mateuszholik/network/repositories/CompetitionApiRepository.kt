package com.mateuszholik.network.repositories

import com.mateuszholik.network.extensions.toResultApi
import com.mateuszholik.network.models.CompetitionDetailsApi
import com.mateuszholik.network.models.ResultApi
import com.mateuszholik.network.models.CompetitionStandingsDetailsApi
import com.mateuszholik.network.models.ScorerApi
import com.mateuszholik.network.services.CompetitionService

interface CompetitionApiRepository {

    suspend fun getCompetition(id: Int): ResultApi<CompetitionDetailsApi>

    suspend fun getCompetitionStandings(id: Int): ResultApi<List<CompetitionStandingsDetailsApi>>

    suspend fun getCompetitionTopScorers(id: Int): ResultApi<List<ScorerApi>>
}

internal class CompetitionApiRepositoryImpl(
    private val competitionService: CompetitionService,
) : CompetitionApiRepository {

    override suspend fun getCompetition(id: Int): ResultApi<CompetitionDetailsApi> =
        competitionService.getCompetitionDetails(id).toResultApi()

    override suspend fun getCompetitionStandings(id: Int): ResultApi<List<CompetitionStandingsDetailsApi>> =
        competitionService.getStandingsOfCompetition(id)
            .toResultApi { this.standingsDetails }

    override suspend fun getCompetitionTopScorers(id: Int): ResultApi<List<ScorerApi>> =
        competitionService.getTopScorersOfCompetition(id).toResultApi { this.scorers }
}
