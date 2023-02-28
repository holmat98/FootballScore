package com.mateuszholik.common.providers

import java.time.LocalDate

interface CurrentDateProvider {

    fun provide(): LocalDate
}

internal class CurrentDateProviderImpl : CurrentDateProvider {

    override fun provide(): LocalDate = LocalDate.now()
}
