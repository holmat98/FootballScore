package com.mateuszholik.domain.usecases.base

import com.mateuszholik.model.Result
import kotlinx.coroutines.flow.Flow

interface ParameterizedFlowUseCase<TInput, TOutput> {

    operator fun invoke(param: TInput): Flow<Result<TOutput>>
}
