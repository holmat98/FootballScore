package com.mateuszholik.data.repositories

import com.mateuszholik.data.extensions.toCommonModel
import com.mateuszholik.data.extensions.toResult
import com.mateuszholik.model.CompetitionDetails
import com.mateuszholik.model.Result
import com.mateuszholik.network.models.CompetitionStandingsDetails
import com.mateuszholik.network.models.ResultApi
import com.mateuszholik.network.models.ScorerApi
import com.mateuszholik.network.repositories.CompetitionApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface CompetitionRepository {

    fun getCompetition(id: Int): Flow<Result<CompetitionDetails>>

    fun getCompetitionStandings(id: Int): Flow<ResultApi<List<CompetitionStandingsDetails>>>

    fun getCompetitionTopScorers(id: Int): Flow<ResultApi<List<ScorerApi>>>
}

internal class CompetitionRepositoryImpl(
    private val competitionApiRepository: CompetitionApiRepository
) : CompetitionRepository {

    override fun getCompetition(id: Int): Flow<Result<CompetitionDetails>> =
        competitionApiRepository.getCompetition(id)
            .map { resultApi ->
                resultApi.toResult {
                    this.toCommonModel()
                }
            }

    override fun getCompetitionStandings(id: Int): Flow<ResultApi<List<CompetitionStandingsDetails>>> {
        TODO("Not yet implemented")
    }

    override fun getCompetitionTopScorers(id: Int): Flow<ResultApi<List<ScorerApi>>> {
        TODO("Not yet implemented")
    }
}
