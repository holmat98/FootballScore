package com.mateuszholik.model

import java.time.LocalDateTime

data class Match(
    val awayTeam: Team,
    val competition: Competition,
    val group: Group,
    val homeTeam: Team,
    val id: Int,
    val lastUpdated: LocalDateTime,
    val matchday: Int,
    val referees: List<Referee>,
    val score: MatchScore,
    val season: Season,
    val stage: Stage,
    val status: Status,
    val utcDate: LocalDateTime,
    val venue: String,
)
