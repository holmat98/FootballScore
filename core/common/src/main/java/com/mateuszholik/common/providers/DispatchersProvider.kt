package com.mateuszholik.common.providers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface DispatchersProvider {

    val io: CoroutineDispatcher
}

internal class DispatchersProviderImpl : DispatchersProvider {

    override val io: CoroutineDispatcher = Dispatchers.IO
}
