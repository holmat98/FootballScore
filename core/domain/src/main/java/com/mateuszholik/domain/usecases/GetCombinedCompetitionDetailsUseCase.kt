package com.mateuszholik.domain.usecases

import com.mateuszholik.data.repositories.CompetitionRepository
import com.mateuszholik.domain.usecases.base.ParameterizedFlowUseCase
import com.mateuszholik.model.CombinedCompetitionDetails
import com.mateuszholik.model.CompetitionDetails
import com.mateuszholik.model.CompetitionStandingsDetails
import com.mateuszholik.model.ErrorType
import com.mateuszholik.model.Result
import com.mateuszholik.model.Scorer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

interface GetCombinedCompetitionDetailsUseCase :
    ParameterizedFlowUseCase<Int, CombinedCompetitionDetails>

internal class GetCombinedCompetitionDetailsUseCaseImpl(
    private val competitionRepository: CompetitionRepository,
) : GetCombinedCompetitionDetailsUseCase {

    override fun invoke(param: Int): Flow<Result<CombinedCompetitionDetails>> =
        combine(
            competitionRepository.getCompetition(param),
            competitionRepository.getCompetitionStandings(param),
            competitionRepository.getCompetitionTopScorers(param)
        ) { competitionResult, competitionStandingsResult, topScorersResult ->

            when {
                competitionResult is Result.Success &&
                competitionStandingsResult is Result.Success &&
                topScorersResult is Result.Success ->
                    createCombinedCompetitionDetailsResult(
                        competitionResult.data,
                        competitionStandingsResult.data,
                        topScorersResult.data
                    )
                competitionResult is Result.Error -> Result.Error(competitionResult.errorType)
                competitionStandingsResult is Result.Error -> Result.Error(
                    competitionStandingsResult.errorType
                )
                topScorersResult is Result.Error -> Result.Error(topScorersResult.errorType)
                else -> Result.Error(ErrorType.UNKNOWN)
            }
        }

    private fun createCombinedCompetitionDetailsResult(
        competitionDetails: CompetitionDetails,
        competitionStandingsDetails: List<CompetitionStandingsDetails>,
        scorers: List<Scorer>,
    ): Result<CombinedCompetitionDetails> =
        Result.Success(
            CombinedCompetitionDetails(
                area = competitionDetails.area,
                id = competitionDetails.id,
                name = competitionDetails.name,
                code = competitionDetails.code,
                type = competitionDetails.type,
                emblem = competitionDetails.emblem,
                currentSeason = competitionDetails.currentSeason,
                seasons = competitionDetails.seasons,
                standingsDetails = competitionStandingsDetails,
                topScorers = scorers
            )
        )
}
