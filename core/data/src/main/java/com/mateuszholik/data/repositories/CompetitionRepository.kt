package com.mateuszholik.data.repositories

import com.mateuszholik.data.extensions.toCommonModel
import com.mateuszholik.data.extensions.toResult
import com.mateuszholik.model.CompetitionDetails
import com.mateuszholik.model.CompetitionStandingsDetails
import com.mateuszholik.model.Result
import com.mateuszholik.model.Scorer
import com.mateuszholik.network.repositories.CompetitionApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface CompetitionRepository {

    fun getCompetition(id: Int): Flow<Result<CompetitionDetails>>

    fun getCompetitionStandings(id: Int): Flow<Result<List<CompetitionStandingsDetails>>>

    fun getCompetitionTopScorers(id: Int): Flow<Result<List<Scorer>>>
}

internal class CompetitionRepositoryImpl(
    private val competitionApiRepository: CompetitionApiRepository,
) : CompetitionRepository {

    override fun getCompetition(id: Int): Flow<Result<CompetitionDetails>> =
        competitionApiRepository.getCompetition(id)
            .map { resultApi ->
                resultApi.toResult {
                    this.toCommonModel()
                }
            }

    override fun getCompetitionStandings(id: Int): Flow<Result<List<CompetitionStandingsDetails>>> =
        competitionApiRepository.getCompetitionStandings(id)
            .map { resultApi ->
                resultApi.toResult {
                    this.map { it.toCommonModel() }
                }
            }

    override fun getCompetitionTopScorers(id: Int): Flow<Result<List<Scorer>>> =
        competitionApiRepository.getCompetitionTopScorers(id)
            .map { resultApi ->
                resultApi.toResult {
                    this.map { it.toCommonModel() }
                }
            }
}
