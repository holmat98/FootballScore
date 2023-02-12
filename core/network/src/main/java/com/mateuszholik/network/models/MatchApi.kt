package com.mateuszholik.network.models

data class MatchApi(
    val area: AreaApi,
    val awayTeam: AwayTeamApi,
    val competition: CompetitionApi,
    val homeTeam: HomeTeamApi,
    val id: Int,
    val lastUpdated: String,
    val matchday: Int,
    val referees: List<RefereeApi>,
    val score: ScoreApi,
    val season: SeasonApi,
    val stage: String,
    val status: String,
    val utcDate: String,
    val venue: String? = null,
)
