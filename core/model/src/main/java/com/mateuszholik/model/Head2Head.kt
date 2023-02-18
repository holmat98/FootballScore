package com.mateuszholik.model

data class Head2Head(
    val awayTeam: TeamH2HData,
    val homeTeam: TeamH2HData,
    val matches: List<Match>,
    val numberOfMatches: Int,
    val totalGoals: Int,
)
