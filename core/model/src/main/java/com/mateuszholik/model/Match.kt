package com.mateuszholik.model

import java.time.LocalDateTime

data class Match(
    val awayTeam: Team,
    val competition: Competition,
    val homeTeam: Team,
    val id: Int,
    val lastUpdated: LocalDateTime,
    val matchday: Int,
    val referees: List<Referee>,
    val score: MatchScore,
    val season: Season,
    val stage: String,
    val status: String,
    val utcDate: LocalDateTime,
    val venue: String? = null,
)
