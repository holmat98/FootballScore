package com.mateuszholik.domain.usecases

import com.mateuszholik.common.providers.DispatchersProvider
import com.mateuszholik.data.repositories.MatchesRepository
import com.mateuszholik.domain.usecases.base.ParameterizedFlowUseCase
import com.mateuszholik.model.Match
import com.mateuszholik.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface GetMatchUseCase : ParameterizedFlowUseCase<Int, Match>

internal class GetMatchUseCaseImpl @Inject constructor(
    private val matchesRepository: MatchesRepository,
    private val dispatchersProvider: DispatchersProvider,
) : GetMatchUseCase {

    override fun invoke(param: Int): Flow<Result<Match>> =
        matchesRepository.getMatch(param)
            .flowOn(dispatchersProvider.io)
}
