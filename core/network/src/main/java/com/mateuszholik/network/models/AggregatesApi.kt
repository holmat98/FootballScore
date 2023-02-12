package com.mateuszholik.network.models

data class AggregatesApi(
    val awayTeam: AwayTeamH2HApi,
    val homeTeam: HomeTeamH2HApi,
    val numberOfMatches: Int,
    val totalGoals: Int
)
