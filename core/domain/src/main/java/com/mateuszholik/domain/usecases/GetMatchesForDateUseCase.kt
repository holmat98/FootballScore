package com.mateuszholik.domain.usecases

import com.mateuszholik.common.providers.DispatchersProvider
import com.mateuszholik.data.repositories.MatchesRepository
import com.mateuszholik.domain.usecases.base.ParameterizedFlowUseCase
import com.mateuszholik.model.Competition
import com.mateuszholik.model.Match
import com.mateuszholik.model.MatchInfo
import com.mateuszholik.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import java.time.LocalDate

interface GetMatchesForDateUseCase :
    ParameterizedFlowUseCase<LocalDate, Map<Competition, List<MatchInfo>>>

internal class GetMatchesForDateUseCaseImpl(
    private val matchesRepository: MatchesRepository,
    private val dispatchersProvider: DispatchersProvider,
) : GetMatchesForDateUseCase {

    override fun invoke(param: LocalDate): Flow<Result<Map<Competition, List<MatchInfo>>>> =
        matchesRepository.getMatchesForDate(param)
            .flowOn(dispatchersProvider.io)
}
