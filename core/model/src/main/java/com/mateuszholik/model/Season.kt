package com.mateuszholik.model

import java.time.LocalDate

data class Season(
    val currentMatchday: Int,
    val endDate: LocalDate,
    val id: Int,
    val startDate: LocalDate,
    val winner: SeasonWinner?,
)
