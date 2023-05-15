package com.mateuszholik.domain.usecases.base

interface ParameterizedSuspendableUseCase<TInput> {

    suspend operator fun invoke(param: TInput)
}
