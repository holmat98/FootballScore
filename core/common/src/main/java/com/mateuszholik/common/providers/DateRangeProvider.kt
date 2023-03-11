package com.mateuszholik.common.providers

import java.time.LocalDate

interface DateRangeProvider {

    fun provide(startingDate: LocalDate, numOfDays: Int): List<LocalDate>
}

internal class DateRangeProviderImpl : DateRangeProvider {

    override fun provide(startingDate: LocalDate, numOfDays: Int): List<LocalDate> =
        List(numOfDays) {
            startingDate.plusDays(it.toLong())
        }
}
