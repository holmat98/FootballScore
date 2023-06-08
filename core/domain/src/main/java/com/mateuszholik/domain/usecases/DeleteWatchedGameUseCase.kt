package com.mateuszholik.domain.usecases

import com.mateuszholik.data.repositories.MatchesRepository
import com.mateuszholik.domain.usecases.base.ParameterizedSuspendableUseCase
import javax.inject.Inject

interface DeleteWatchedGameUseCase : ParameterizedSuspendableUseCase<Int>

internal class DeleteWatchedGameUseCaseImpl @Inject constructor(
    private val matchesRepository: MatchesRepository,
) : DeleteWatchedGameUseCase {

    override suspend fun invoke(param: Int) =
        matchesRepository.deleteWatchedGame(param)
}
