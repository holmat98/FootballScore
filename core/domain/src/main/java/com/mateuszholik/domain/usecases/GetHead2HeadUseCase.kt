package com.mateuszholik.domain.usecases

import com.mateuszholik.common.providers.DispatchersProvider
import com.mateuszholik.data.repositories.MatchesRepository
import com.mateuszholik.domain.usecases.base.ParameterizedFlowUseCase
import com.mateuszholik.model.Head2Head
import com.mateuszholik.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface GetHead2HeadUseCase : ParameterizedFlowUseCase<Int, Head2Head>

internal class GetHead2HeadUseCaseImpl @Inject constructor(
    private val matchesRepository: MatchesRepository,
    private val dispatchersProvider: DispatchersProvider,
) : GetHead2HeadUseCase {

    override fun invoke(param: Int): Flow<Result<Head2Head>> =
        matchesRepository.getMatchH2H(param)
            .flowOn(dispatchersProvider.io)
}
