package com.mateuszholik.model

data class Match(
    val awayTeam: Team,
    val competition: Competition,
    val homeTeam: Team,
    val id: Int,
    val lastUpdated: String,
    val matchday: Int,
    val referees: List<Referee>,
    val score: MatchScore,
    val season: Season,
    val stage: String,
    val status: String,
    val utcDate: String,
    val venue: String? = null,
)
