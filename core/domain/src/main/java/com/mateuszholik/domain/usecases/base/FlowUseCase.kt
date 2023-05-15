package com.mateuszholik.domain.usecases.base

import kotlinx.coroutines.flow.Flow
import com.mateuszholik.model.Result

interface FlowUseCase<TOutput> {

    operator fun invoke(): Flow<Result<TOutput>>
}
