package com.mateuszholik.model

data class AggregatedH2HData(
    val awayTeam: TeamH2HData,
    val homeTeam: TeamH2HData,
    val numberOfMatches: Int,
    val totalGoals: Int,
)
