package com.mateuszholik.domain.usecases

import com.mateuszholik.common.providers.DispatchersProvider
import com.mateuszholik.data.repositories.MatchesRepository
import com.mateuszholik.domain.usecases.base.FlowUseCase
import com.mateuszholik.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface GetWatchedGamesIdsUseCase : FlowUseCase<List<Int>>

internal class GetWatchedGamesIdsUseCaseImpl @Inject constructor(
    private val matchesRepository: MatchesRepository,
    private val dispatchersProvider: DispatchersProvider,
): GetWatchedGamesIdsUseCase {

    override fun invoke(): Flow<Result<List<Int>>> =
        matchesRepository.getWatchedMatchesId()
            .flowOn(dispatchersProvider.io)
}
