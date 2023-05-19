package com.mateuszholik.domain.usecases

import com.mateuszholik.common.providers.DispatchersProvider
import com.mateuszholik.data.repositories.MatchesRepository
import com.mateuszholik.domain.usecases.base.FlowUseCase
import com.mateuszholik.model.Competition
import com.mateuszholik.model.MatchInfo
import com.mateuszholik.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface GetWatchedGamesUseCase : FlowUseCase<Map<Competition, List<MatchInfo>>>

internal class GetWatchedGamesUseCaseImpl @Inject constructor(
    private val matchesRepository: MatchesRepository,
    private val dispatchersProvider: DispatchersProvider,
) : GetWatchedGamesUseCase {

    override fun invoke(): Flow<Result<Map<Competition, List<MatchInfo>>>> =
        matchesRepository.getWatchedMatches()
            .flowOn(dispatchersProvider.io)
}