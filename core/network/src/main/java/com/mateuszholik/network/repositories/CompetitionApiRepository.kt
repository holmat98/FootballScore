package com.mateuszholik.network.repositories

import com.mateuszholik.network.extensions.toResultApi
import com.mateuszholik.network.models.CompetitionDetailsApi
import com.mateuszholik.network.models.ResultApi
import com.mateuszholik.network.models.CompetitionStandingsDetails
import com.mateuszholik.network.models.ScorerApi
import com.mateuszholik.network.services.CompetitionService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface CompetitionApiRepository {

    fun getCompetition(id: Int): Flow<ResultApi<CompetitionDetailsApi>>

    fun getCompetitionStandings(id: Int): Flow<ResultApi<List<CompetitionStandingsDetails>>>

    fun getCompetitionTopScorers(id: Int): Flow<ResultApi<List<ScorerApi>>>
}

internal class CompetitionApiRepositoryImpl(
    private val competitionService: CompetitionService,
) : CompetitionApiRepository {

    override fun getCompetition(id: Int): Flow<ResultApi<CompetitionDetailsApi>> =
        flow {
            emit(competitionService.getCompetitionDetails(id).toResultApi())
        }

    override fun getCompetitionStandings(id: Int): Flow<ResultApi<List<CompetitionStandingsDetails>>> =
        flow {
            emit(
                competitionService.getStandingsOfCompetition(id)
                    .toResultApi { this.standingsDetails }
            )
        }

    override fun getCompetitionTopScorers(id: Int): Flow<ResultApi<List<ScorerApi>>> =
        flow {
            emit(
                competitionService.getTopScorersOfCompetition(id).toResultApi { this.scorers }
            )
        }
}
