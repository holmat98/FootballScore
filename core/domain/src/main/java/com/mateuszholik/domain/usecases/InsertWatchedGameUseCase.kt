package com.mateuszholik.domain.usecases

import com.mateuszholik.data.repositories.MatchesRepository
import com.mateuszholik.domain.usecases.base.ParameterizedSuspendableUseCase
import javax.inject.Inject

interface InsertWatchedGameUseCase : ParameterizedSuspendableUseCase<Int>

internal class InsertWatchedGameUseCaseImpl @Inject constructor(
    private val matchesRepository: MatchesRepository,
) : InsertWatchedGameUseCase {

    override suspend fun invoke(param: Int) =
        matchesRepository.insertWatchedGame(param)
}
