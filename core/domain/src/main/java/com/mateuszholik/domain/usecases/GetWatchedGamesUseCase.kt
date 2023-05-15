package com.mateuszholik.domain.usecases

import com.mateuszholik.data.repositories.MatchesRepository
import com.mateuszholik.domain.usecases.base.FlowUseCase
import com.mateuszholik.model.Competition
import com.mateuszholik.model.MatchInfo
import com.mateuszholik.model.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetWatchedGamesUseCase : FlowUseCase<Map<Competition, List<MatchInfo>>>

internal class GetWatchedGamesUseCaseImpl @Inject constructor(
    private val matchesRepository: MatchesRepository,
) : GetWatchedGamesUseCase {

    override fun invoke(): Flow<Result<Map<Competition, List<MatchInfo>>>> =
        matchesRepository.getMatchesForIds()
}
